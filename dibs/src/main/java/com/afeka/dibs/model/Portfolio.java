package com.afeka.dibs.model;

import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PORTFOLIOS")
public class Portfolio {

	@Id
	@GeneratedValue
	private Long id;
	private Long accountId;
	private Double value;
	private Double balance;
	
	@Embedded
	@ElementCollection
	private ArrayList<StockInPortfolio> stocks;
	
	
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

	public ArrayList<StockInPortfolio> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<StockInPortfolio> stocks) {
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
