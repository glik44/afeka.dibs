package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afeka.dibs.model.Stock;

public interface StockDao extends JpaRepository<Stock, String> {
}
