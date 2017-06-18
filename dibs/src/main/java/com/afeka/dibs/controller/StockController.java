package com.afeka.dibs.controller;

import java.util.List;

import stockexchange.client.StockExchangeClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afeka.dibs.model.Stock;
import com.afeka.dibs.service.StockService;


@RestController
@RequestMapping(path="/stock")
public class StockController {

	@Autowired
	private StockExchangeClient client;
	@Autowired
	private StockService stockService;

	
	public StockController(){
	}
		
//	@Bean
//	@Autowired
//	public StockExchangeClient client(){
//	    return new StockExchangeClientImplementation();
//	}
	
	@Scheduled(fixedDelay=350000)
	public void UpdateStocks (){
		List<String> stocksIds= client.getStocksId();
		Stock stockTemp;
		for(int i=0 ; i< stocksIds.size() ;i++){
			stockexchange.client.Stock s= client.getQuote(stocksIds.get(i));
			stockTemp = new Stock(s.getId(),s.getName(),s.getQuote());
			stockService.add(stockTemp);
		}
	}
	
	@RequestMapping(path="/showstock/{stockId}", method=RequestMethod.GET)
	public Stock showStock (@PathVariable("stockId") String stockId){
		return stockService.getById(stockId);
	}
	
	@RequestMapping(path="/showall", method=RequestMethod.GET)
	public List<Stock> showAllStock (){
		return stockService.getAll();
	}
	
	
}
