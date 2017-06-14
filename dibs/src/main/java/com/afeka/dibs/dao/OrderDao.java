package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afeka.dibs.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

}
