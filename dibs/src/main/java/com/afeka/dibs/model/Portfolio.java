package com.afeka.dibs.model;

import java.util.List;

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
	
	@Embedded
	private List<StockInPortfolio> stocks;

	public Portfolio(Long id, Long accountId, List<StockInPortfolio> stocks) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.stocks = stocks;
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
	
	
	
	
	
}
