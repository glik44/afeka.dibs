package com.afeka.dibs.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class StockInPortfolio {
	
	private String stockId;
	private Long StockInPortfolioId;//equal to tansactionId
	private Integer amount;
	private Double purchaseRate;
	private Date timeStamp; 
	
	public StockInPortfolio() {
		super();
	}

	public StockInPortfolio(String stockId, Long StockInPortfolioId, Integer amount, Double purchaseRate, Date timeStamp) {
		super();
		this.stockId = stockId;
		this.StockInPortfolioId = StockInPortfolioId;
		this.amount = amount;
		this.purchaseRate = purchaseRate;
		this.timeStamp = timeStamp;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	
	public Long getStockInPortfolioId() {
		return StockInPortfolioId;
	}

	public void setStockInPortfolioId(Long stockInPortfolioId) {
		StockInPortfolioId = stockInPortfolioId;
	}

	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	
	
	

}
