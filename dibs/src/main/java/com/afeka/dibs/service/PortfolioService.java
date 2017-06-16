package com.afeka.dibs.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afeka.dibs.dao.PortfolioDao;
import com.afeka.dibs.model.Portfolio;

@Service
public class PortfolioService implements Iservice<Portfolio>{
	
	@Autowired
	private PortfolioDao portfolioDao;
	
	@Override
	@Transactional
	public List<Portfolio> add(Portfolio... objects) {
		return portfolioDao.save(Arrays.asList(objects));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Portfolio> getAll() {
		return portfolioDao.findAll();
	}
	
	@Transactional(readOnly=true)
	public Portfolio getById(Long id) {
		return portfolioDao.getOne(id);
	}
	
	@Transactional(readOnly=true)
	public List<Portfolio> getPortfolioByAccount (Long accountId){
		return portfolioDao.getPortfolioByAccount(accountId);
	}
	

}
