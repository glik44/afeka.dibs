package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import stockexchange.client.StockCommandType;
import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeCommand;
import stockexchange.client.StockExchangeTransaction;

import com.afeka.dibs.model.Order;
import com.afeka.dibs.model.StockInPortfolio;
import com.afeka.dibs.model.OrderStatus;
import com.afeka.dibs.model.OrderType;
import com.afeka.dibs.model.Portfolio;
import com.afeka.dibs.service.OrderService;
import com.afeka.dibs.service.PortfolioService;



@RestController
@RequestMapping(path="/order")
public class OrderController {
	private static final String clientId = "client1";
	private static final double fee = 2.5;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private PortfolioService portfolioService;

	
	@Autowired
	private StockExchangeClient client;
	
	public OrderController(){
	}
		
	@RequestMapping(path="/placeorder",
			method=RequestMethod.POST)
	public String PlaceOrder(@RequestBody Order order){
		StockExchangeCommand command=null;
		
		if(order.getPortfolioId() == null)
			return "invalid portfolio number!";
		
		if(order.getPaymentMethod() == null)
			return "invalid payment method!";
		
		Portfolio p = portfolioService.getById(order.getPortfolioId());
		if(order.getType() == OrderType.ASK){
			if(p.getStockAmountAvailable(order.getStockId()) >= order.getAmount())
				command = new StockExchangeCommand(StockCommandType.ASK, clientId,
													order.getStockId(), order.getMinPrice(),
													order.getMaxPrice(), order.getAmount());
			else
				return "You dont have enough stocks of:" + order.getStockId();
		}
		else if(order.getType() == OrderType.BID){
			command = new StockExchangeCommand(StockCommandType.BID, clientId,
												order.getStockId(), order.getMinPrice(),
												order.getMaxPrice(), order.getAmount());
		}
		
		if(command != null){
			order.setId(this.client.sendCommand(command));
			order.setStatus(OrderStatus.PERFORMED);
			orderService.add(order);
			charge(order.getPaymentMethod(), fee);
			return "Order sent to stock exchange!\n order number:" + order.getId();
		}
		return "Order failed!";
	}
	
	@Scheduled(fixedDelay=60000)
	public void UpdateOrders (){
		List<Order> pendingOrder= orderService.getAllWaitingOrders();
		
		for (Order order : pendingOrder) {
			Portfolio p = portfolioService.getById(order.getPortfolioId());
			List<StockExchangeTransaction> t = this.client.getTransactionsForCommand(order.getId());
			
			//ASK - sell
			if (order.getType() == OrderType.ASK){
				for (StockExchangeTransaction st : t) {
					Integer actualAmount = st.getActualAmount();
					order.setAmountCommited(order.getAmountCommited() + actualAmount);
					charge(order.getPaymentMethod(),st.getActualPrice()*st.getActualAmount());
					for (StockInPortfolio sip :p.getStocks()){
						if (sip.getStockId().equals(order.getStockId())){
							if (sip.getAmount() <= actualAmount){
								actualAmount -= sip.getAmount();
								p.setBalance(p.getBalance() - (sip.getAmount()*sip.getPurchaseRate()));
								p.getStocks().remove(sip);
							}
							else{
								sip.setAmount(sip.getAmount() - actualAmount);
								p.setBalance(p.getBalance() - (actualAmount*sip.getPurchaseRate()));
								actualAmount = 0;
							}
							if(actualAmount == 0)
								break;
						}
					}
				}
			}
			
			else if(order.getType() == OrderType.BID){
				for (StockExchangeTransaction st : t) {
					Integer actualAmount = st.getActualAmount();
					order.setAmountCommited(order.getAmountCommited() + actualAmount);
					charge(order.getPaymentMethod(),st.getActualPrice()*st.getActualAmount());
					p.getStocks().add(new StockInPortfolio(order.getStockId(), st.getId(), st.getActualAmount(), 
															st.getActualPrice(), st.getTimestamp()));
					p.setBalance(p.getBalance() + (st.getActualAmount() * st.getActualPrice()));
				}
			}
			
			if(order.getAmountCommited() == order.getAmount())
				order.setStatus(OrderStatus.COMMITTED);
			portfolioService.add(p);
			orderService.add(order);
		}
	}
	
	@RequestMapping(path="/showorder/{portfolioId}", method=RequestMethod.GET)
	public List<Order> showOrdersByPortfolio (@PathVariable("portfolioId") Long portfolioId){
		return this.orderService.getOrdersByPortfolio(portfolioId);
	}
	
	public boolean charge(String paymentMethod, double price){
		return true;
	}
}
