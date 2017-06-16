package com.afeka.dibs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afeka.dibs.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
}
