package com.afeka.dibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		System.out.println(account.toString());
		if(account != null){
			if ((accountService.add(account)).size() > 0)
				return "{\"status\": \"The account created successfuly\"}";
		}
		return "Error to create account";
	}
	
	@RequestMapping(path="/showall", method=RequestMethod.GET)
	public List<Account> showAllAccount (){
		return accountService.getAll();
	}
	
	@RequestMapping(path="/login",
			method=RequestMethod.GET)
	public String login (@RequestParam("accountId") Long accountId, @RequestParam("password") String password){
		
		Account a = accountService.getById(accountId);
		if(a == null)
			return "Account not exsit";
		else{
			if(a.getPassword().equals(password))
				return "Login success\n Welcome" + a.getName();
			else
				return "wrong password, try again";
		}
	}
	
}
