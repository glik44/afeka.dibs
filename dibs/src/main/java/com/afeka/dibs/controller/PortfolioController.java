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
	public List<Portfolio> showPortfolio(@RequestParam("accountId") Long accountId){
		List<Portfolio> portfolioList = portfolioService.getPortfolioByAccount(accountId);
		if(portfolioList != null)
			for (Portfolio portfolio : portfolioList) {
				portfolio.setValue(calcProtfolioValue(portfolio));
				portfolio.setProfit(calcProtfolioProfit(portfolio));
				portfolioService.add(portfolio);
			}
		return portfolioList;
	}
	
	public Double calcProtfolioValue(Portfolio portfolio){
		Double value = 0.0;
		List<StockInPortfolio> stocksList = portfolio.getStocks();
		if(stocksList != null)
			for (StockInPortfolio stockInPortfolio : stocksList) {
				value +=stockService.getById(stockInPortfolio.getStockId()).getQuote() * stockInPortfolio.getAmount();
			}

		return value;
	}
	
	public Double calcProtfolioProfit(Portfolio portfolio){
		Double profit;
		profit = (portfolio.getValue() / portfolio.getBalance()) * 100 ;
		
		return profit;
	}
}
