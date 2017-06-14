package com.afeka.dibs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Portfolio {

	@Id
	@GeneratedValue
	private Long id;
	private Long accountId;
	
}
