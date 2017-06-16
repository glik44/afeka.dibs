package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import stockexchange.client.StockCommandType;
import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeClientImplementation;
import stockexchange.client.StockExchangeCommand;
import stockexchange.client.StockExchangeTransaction;

import com.afeka.dibs.model.Order;
import com.afeka.dibs.model.Stock;
import com.afeka.dibs.model.StockInPortfolio;
import com.afeka.dibs.model.OrderStatus;
import com.afeka.dibs.model.OrderType;
import com.afeka.dibs.model.Portfolio;
import com.afeka.dibs.service.OrderService;
import com.afeka.dibs.service.PortfolioService;
import com.afeka.dibs.service.StockService;


@RestController
@RequestMapping(path="/order")
public class OrderController {
	private static final String clientId = "client1";
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private PortfolioService portfolioService;
	@Autowired
	private StockService stockService;
	
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
		if(order.getType() == OrderType.ASK){
			command = new StockExchangeCommand(StockCommandType.ASK, clientId,
																	order.getStockId(), order.getMinPrice(),
																	order.getMaxPrice(), order.getAmount());
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
			return "Order sent to stock exchange!\n order number:" + order.getId();
		}
		return "Something worng!";
	}
	
	@Scheduled(fixedDelay=60000)
	public void UpdateOrders (){
		List<Order> pendingOrder= orderService.getAllWaitingOrders();
		
		for (Order order : pendingOrder) {
			int amount=0;
			Portfolio p = portfolioService.getById(order.getPortfolioId());
			List<StockExchangeTransaction> t = this.client.getTransactionsForCommand(order.getId());
			for (StockExchangeTransaction st : t) {
				amount += st.getActualAmount();
				p.getStocks().add(new StockInPortfolio(order.getStockId(), st.getActualAmount(), 
															st.getActualPrice(),st.getTimestamp()));
			}
			portfolioService.add(p);
			//TODO
		}
	}
	
	@RequestMapping(path="/showorder/{portfolioId}", method=RequestMethod.GET)
	public List<Order> showOrdersByPortfolio (@PathVariable("portfolioId") Long portfolioId){
		return this.orderService.getOrdersByPortfolio(portfolioId);
	}
}
