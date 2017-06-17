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
	private Double value;
	private Double balanc;
	
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getBalanc() {
		return balanc;
	}

	public void setBalanc(Double balanc) {
		this.balanc = balanc;
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
