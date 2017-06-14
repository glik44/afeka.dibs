package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afeka.dibs.model.Account;
import com.afeka.dibs.service.AccountService;

@RestController
@RequestMapping(path="/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	public AccountController(){
	}
	
	public AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@RequestMapping(path="/new",
			method=RequestMethod.POST)
	public String addNewAccount (@RequestBody Account account){
		if(account != null){
			if ((accountService.add(account)).size() > 0)
				return "The account created successfuly";
		}
		return "Error to create account";
	}
	
	@RequestMapping(path="/showall", method=RequestMethod.GET)
	public List<Account> showAllAccount (){
		return accountService.getAll();
	}
	
}
