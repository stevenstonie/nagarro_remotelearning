package com.iquestgroup.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
	@Test
	public void testDeposit() {
		Account account = new Account("John Cena", 123456789, 1000);
		account.deposit(100);
		assertEquals(1100, account.getBalance());

		account.deposit(-100);
		assertEquals(1100, account.getBalance());
	}

	@Test
	public void testWithdraw() {
		Account account = new Account("Joe Mama", 987654321, 1000);
		account.withdraw(100, 10);
		assertEquals(890, account.getBalance());

		account.withdraw(-100, 10);
		assertEquals(890, account.getBalance());

		account.withdraw(1000, 100);
		assertEquals(890, account.getBalance());
	}

	@Test
	public void testAddInterest() {
		Account account = new Account("John Cena", 123456789, 1000);
		account.addInterest();
		assertEquals(1045, account.getBalance());

		account.addInterest();
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.000");
		assertEquals("1092.025", df.format(account.getBalance()));

		account.addInterest();
		assertEquals("1141.166", df.format(account.getBalance()));
	}
}
