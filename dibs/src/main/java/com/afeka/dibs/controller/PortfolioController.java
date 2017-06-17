package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afeka.dibs.model.Portfolio;
import com.afeka.dibs.model.StockInPortfolio;
import com.afeka.dibs.service.PortfolioService;
import com.afeka.dibs.service.StockService;

@RestController
@RequestMapping(path="/portfolio")
public class PortfolioController {
	@Autowired
	private PortfolioService portfolioService;
	@Autowired
	private StockService stockService;
	
	
	public PortfolioController(){
	}

	public PortfolioController(PortfolioService portfolioService){
		this.portfolioService = portfolioService;
	}
	
	@RequestMapping(path="/create/{accountId}", method=RequestMethod.GET)
	public String createPortfolio(@PathVariable("accountId") Long accountId){
		Portfolio p = new Portfolio(accountId);
		if((portfolioService.add(p)).size() > 0){
			return "The portfolio created successfuly! ID:" + p.getId();
		}
		return "Error to open new portfolio";
	}
	
	@RequestMapping(path="/show", method=RequestMethod.GET)
	public List<Portfolio> showPortfolio(@RequestParam("accountId") Long accountId ,@RequestParam("protfolioId") Long protfolioId){
		List<Portfolio> protfolioList = portfolioService.getPortfolioByAccount(accountId);
		for(int i=0 ; i < protfolioList.size() ; i++){
			Portfolio protfolioTemp = protfolioList.get(i);
			protfolioTemp.setValue(calcProtfolioValue(protfolioId));
			protfolioTemp.setBalanc(calcProtfolioBalanc(protfolioId));
		}
		return protfolioList;
	}
	
	public Double calcProtfolioValue(Long protfolioId){
		Double value = 0.0;
		List<StockInPortfolio> stocksList = this.portfolioService.getById(protfolioId).getStocks();
		for(int i=0 ; i < stocksList.size() ; i++){
			StockInPortfolio stockTemp = stocksList.get(i);
			value +=stockService.getById(stockTemp.getStockId()).getQuote() * stockTemp.getAmount();
		}
		return value;
	}
	
	public Double calcProtfolioBalanc(Long protfolioId){
		Double balanc = 0.0;
		List<StockInPortfolio> stocksList = (this.portfolioService.getById(protfolioId)).getStocks();
		balanc = calcProtfolioValue(protfolioId);
		for(int i=0 ; i < stocksList.size() ; i++){
			StockInPortfolio stockTemp = stocksList.get(i);
			balanc -= (stockTemp.getPurchaseRate() * stockTemp.getAmount());
		}
		return balanc;
	}
}
