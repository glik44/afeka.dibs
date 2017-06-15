package com.afeka.dibs.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeClientFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
	private StockService stockService;
	private StockExchangeClient client;
	
	private TimerListener timeListener;
	private Timer timer;
	
	public StockController(){
	}
	
	public StockController(StockService stockService){
		this.stockService = stockService;
		this.client = StockExchangeClientFactory.getClient();
		this.timeListener = new TimerListener();
		 this.timer = new Timer(20000, timeListener);
		 this.timer.start();
	}
	
	
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
	
	private class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			UpdateStocks();
		}
		
	}
	
}
