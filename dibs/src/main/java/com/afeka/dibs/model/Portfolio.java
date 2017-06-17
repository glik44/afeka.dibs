package com.afeka.dibs.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="PORTFOLIOS")
public class Portfolio {

	@Id
	@GeneratedValue
	private Long id;
	private Long accountId;
	
	@Embedded
	@Autowired
	private List<StockInPortfolio> stocks;
	
	
	public Portfolio() {
		super();
	}

	public Portfolio(Long accountId) {
		super();
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public List<StockInPortfolio> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockInPortfolio> stocks) {
		this.stocks = stocks;
	}
	
	// this methods needed to check if there is already a trassactionID. 
	public boolean checkIfStockInPortfolioIdExict(Long stockInPortfolioId){ 
		for (StockInPortfolio stockInPortfolio : stocks)
			if (stockInPortfolio.getStockInPortfolioId()==stockInPortfolioId)
				return true;
		return false;
	}
	
	public StockInPortfolio getStockInPortfolioByStockInPortfolioId(Long StockInPortfolioId){
		for (StockInPortfolio stockInPortfolio : stocks){
			if (stockInPortfolio.getStockInPortfolioId() == StockInPortfolioId)
				return stockInPortfolio;
		}
		return null;	
	}

}
