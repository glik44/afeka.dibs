package com.afeka.dibs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.afeka.dibs.model.Stock;

public interface StockDao extends JpaRepository<Stock, String> {
	@Query("UPDATE s FROM stock AS s WHERE s.id = :stockId ORDER BY s.id ASC")
	public List<Stock> updateStockById (@Param("stockId") Long accountId);
}
