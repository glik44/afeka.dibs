package com.afeka.dibs.model;

import javax.persistence.Embeddable;

@Embeddable
public class StockInPortfolio {
	
	private String stockId;
	private Integer amount;
	private Double purchaseRate;
	
	
	public StockInPortfolio() {
		super();
	}

	public StockInPortfolio(String stockId, Integer amount, Double purchaseRate) {
		super();
		this.stockId = stockId;
		this.amount = amount;
		this.purchaseRate = purchaseRate;
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
