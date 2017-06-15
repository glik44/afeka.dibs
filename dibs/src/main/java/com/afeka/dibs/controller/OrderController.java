package com.afeka.dibs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import stockexchange.client.StockCommandType;
import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeClientFactory;
import stockexchange.client.StockExchangeCommand;

import com.afeka.dibs.model.Account;
import com.afeka.dibs.model.Order;
import com.afeka.dibs.service.OrderService;


@RestController
@RequestMapping(path="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	private StockExchangeClient client;
	
	public OrderController(){
	}
	
	public OrderController(OrderService orderService){
		this.orderService =orderService;
		this.client = StockExchangeClientFactory.getClient();
	}
	
	public void PlaceOrder(@RequestBody Order order){
		StockExchangeCommand command = new StockExchangeCommand(null, order.getInvokerId(),
				order.getStockId(), order.getMinPrice(),
				order.getMaxPrice(), order.getAmount()); // need to add order type
		order.setId(this.client.sendCommand(command));
	}
	
	@RequestMapping(path="/showorder/{orderId}", method=RequestMethod.GET)
	public Order showOrder (@PathVariable("orderId") Long orderId){
		return this.orderService.getById(orderId);
	}
}
