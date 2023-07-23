package com.iquestgroup.bank;

public class BankSimulation {
    public static void main(String[] args) {
        Account account = new Account("George", 1L, 100);

        account.withdraw(10, 0.2f);

        System.out.println("Your balance: " + account.getBalance());

        account.withdraw(10, 0.2f);

        System.out.println("Your balance: " + account.getBalance());
    }
}
// i wouldve thought the program would give an error because of the 1L before i saw that the data type was long
// other than that the predicted output was 89.8 and 79.6
