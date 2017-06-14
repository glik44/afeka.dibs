package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afeka.dibs.model.Account;

public interface AccountDao extends JpaRepository<Account, Long> {
}
