package com.afeka.dibs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;


@Entity
@Table(name="PORTFOLIOS")
public class Portfolio {

	@Id
	@GeneratedValue
	private Long id;
	private Long accountId;
	private Double value;
	private Double balance;
	private Double profit;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="StockInPortfolio",
		joinColumns= @JoinColumn(name="portfolio_id"))
	private List<StockInPortfolio> stocks = new ArrayList<StockInPortfolio>();

	
	public Portfolio() {
		super();
	}

	public Portfolio(Long accountId) {
		super();
		this.accountId = accountId;	
		this.balance = 0.0;
		this. value = 0.0;
		this.profit = 0.0;
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
	
	public int getStockAmountAvailable(String stockId){
		int amount=0;
		for(StockInPortfolio stockInPortfolio : stocks){
			if(stockInPortfolio.getStockId().equals(stockId)){
				amount += stockInPortfolio.getAmount();
			}
		}
		return amount;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

}
