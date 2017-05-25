package com.openwebinars.hibernate.concurrency.pessimistic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class UserAccount {

	@Id
	private int id;

	private String name;
	
	private double balance;
	
	@Version
	private Long version;
	
	
	public UserAccount() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}
	
	



}