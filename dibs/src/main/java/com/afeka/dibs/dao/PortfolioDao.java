package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afeka.dibs.model.Portfolio;

public interface PortfolioDao extends JpaRepository<Portfolio, Long> {

}
