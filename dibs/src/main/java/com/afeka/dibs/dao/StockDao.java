package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afeka.dibs.model.Stock;

@Repository
public interface StockDao extends JpaRepository<Stock, String> {
}
