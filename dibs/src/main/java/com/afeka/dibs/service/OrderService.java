package com.afeka.dibs.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.afeka.dibs.dao.OrderDao;
import com.afeka.dibs.model.Order;

public class OrderService implements Iservice <Order> {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional
	public List<Order> add(Order... objects) {
		return orderDao.save(Arrays.asList(objects));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Order> getAll() {
		return orderDao.findAll();
	}

	@Transactional(readOnly=true)
	public Order getById(Long id) {
		return orderDao.getOne(id);
	}
}
