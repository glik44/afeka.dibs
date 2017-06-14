package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afeka.dibs.model.Account;
import com.afeka.dibs.model.Stock;
import com.afeka.dibs.service.AccountService;
import com.afeka.dibs.service.StockService;

@RestController
@RequestMapping(path="/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
	public StockController(){
	}
	
	public StockController(StockService stockService){
		this.stockService = stockService;
	}
	
//	@RequestMapping(path="/updateStock",
//			method=RequestMethod.POST)
//	public String addNewStock (@RequestBody Stock stock){
//		
//	}
	
	@RequestMapping(path="/showstock/{stockId}", method=RequestMethod.GET)
	public Stock showStock (@PathVariable("stockId") String stockId){
		return stockService.getById(stockId);
	}
	
	@RequestMapping(path="/showall", method=RequestMethod.GET)
	public List<Stock> showAllStock (){
		return stockService.getAll();
	}
}
