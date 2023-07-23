package com.nagarro.remotelearning.classes;

public class ClientAccount {
	private Integer clientId;
	private String username;
	private String email;
	private Integer accountId;
	private Double balance;

	public ClientAccount(Integer clientId, String username, String email, Integer accountId, Double balance) {
		this.clientId = clientId;
		this.username = username;
		this.email = email;
		this.accountId = accountId;
		this.balance = balance;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
