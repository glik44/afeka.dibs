package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afeka.dibs.model.Portfolio;
import com.afeka.dibs.service.PortfolioService;

@RestController
@RequestMapping(path="/portfolio")
public class PortfolioController {
	@Autowired
	private PortfolioService portfolioService;
	
	
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
	
	@RequestMapping(path="/show/{accountId}", method=RequestMethod.GET)
	public List<Portfolio> showPortfolio(@PathVariable("accountId") Long accountId){
		return portfolioService.getPortfolioByAccount(accountId);
	}
}
