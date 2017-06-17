package com.afeka.dibs.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afeka.dibs.dao.AccountDao;
import com.afeka.dibs.model.Account;

@Service
public class AccountService implements Iservice <Account>{
	
	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional
	public List<Account> add(Account... objects) {
		return accountDao.save(Arrays.asList(objects));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Account> getAll() {
		return accountDao.findAll();
	}

	@Transactional(readOnly=true)
	public Account getById(Long id) {
		return accountDao.findOne(id);
	}
}
