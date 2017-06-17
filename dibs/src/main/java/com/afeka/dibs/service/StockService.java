package com.afeka.dibs.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afeka.dibs.dao.StockDao;
import com.afeka.dibs.model.Stock;

@Service
public class StockService implements Iservice<Stock>{

	@Autowired
	private StockDao stockDao;

	@Override
	@Transactional
	public List<Stock> add(Stock... objects) {
		return stockDao.save(Arrays.asList(objects));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Stock> getAll() {
		return stockDao.findAll();
	}

	@Transactional(readOnly=true)
	public Stock getById(String id) {
		return stockDao.findOne(id);
	}
	

}
