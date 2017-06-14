package com.afeka.dibs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STOCKS")
public class Stock {

	@Id
	private String id;
	private String name;
	private Double quote ;
	
	public Stock() {
		super();
	}

	public Stock(String id, String name, Double quote){
		this.id = id;
		this.name = name;
		this.quote = quote;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getQuote() {
		return quote;
	}
	public void setQuote(Double quote) {
		this.quote = quote;
	}
}
