package com.afeka.dibs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.afeka.dibs.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order AS o WHERE o.portfolioId = :portfolioID ORDER BY o.id ASC")
	public List<Order> getOrdersByPortfolio (@Param("portfolioID") Long portfolioId);
	
	@Query("SELECT o FROM Order AS o WHERE o.status = com.afeka.dibs.model.OrderStatus.PERFORMED")
	public List<Order> getAllWaitingOrders ();
}
