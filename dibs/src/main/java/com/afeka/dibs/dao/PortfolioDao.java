package com.afeka.dibs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.afeka.dibs.model.Portfolio;

public interface PortfolioDao extends JpaRepository<Portfolio, Long> {
	@Query("SELECT p FROM Portfolio AS p WHERE p.accountId = :accountID ORDER BY p.id ASC")
	public List<Portfolio> getPortfolioByAccount (@Param("accountID") Long accountId);
}
