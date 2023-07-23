package com.nagarro.remotelearning.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsRepository {
	private final Connection connection;

	public AccountsRepository(Connection connection) {
		this.connection = connection;
	}

	public boolean createAccountsTable() {
		try (PreparedStatement queryRunner = connection.prepareStatement("create table if not exists accounts ("
				+ "id serial primary key, "
				+ "balance decimal (10, 2), "
				+ "client_id int, "
				+ "foreign key (client_id) references clients(id));")) {

			queryRunner.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.err.println("SQLException caught while creating the accounts table: " + e.getMessage());
		}
		return false;
	}

	public boolean insertAccount(Double balance, Integer clientId) {
		if (clientId == null) {
			System.err.println("client id is null");
			return false;
		}

		try (PreparedStatement queryRunner = connection
				.prepareStatement("insert into accounts (balance, client_id) values (?, ?)")) {

			queryRunner.setDouble(1, balance);
			queryRunner.setInt(2, clientId);

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while creating account: " + e.getMessage());
		}
		return false;
	}

	public boolean updateAccount(Integer id, Double balance) {
		try (PreparedStatement queryRunner = connection.prepareStatement(
				"update accounts set balance = ? where id = ?")) {

			queryRunner.setDouble(1, balance);
			queryRunner.setInt(2, id);

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while updating account: " + e.getMessage());
		}
		return false;
	}

	public Integer getAccountIdByClientId(Integer clientId) {
		try (PreparedStatement queryRunner = connection.prepareStatement(
				"select id from accounts where client_id = ?")) {

			queryRunner.setInt(1, clientId);

			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next()) {
				return queryOutput.getInt("id");
			}

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting account id by client id: " + e.getMessage());
		}
		return -1;
	}

	public boolean addBalanceToClientAccount(Integer clientId, Double balance) {
		Integer accountId = getAccountIdByClientId(clientId);

		if (accountId == null) {
			System.err.println("account id is null");
			return false;
		}

		try (PreparedStatement queryRunner = connection.prepareStatement(
				"update accounts set balance = balance + ? where id = ?")) {

			queryRunner.setDouble(1, balance);
			queryRunner.setInt(2, accountId);

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while adding balance to account: " + e.getMessage());
		}
		return false;
	}

	public boolean substractBalanceFromClientAccount(Integer clientId, Double balance) {
		Integer accountId = getAccountIdByClientId(clientId);

		if (accountId == null) {
			System.err.println("account id is null");
			return false;
		}

		try (PreparedStatement queryRunner = connection.prepareStatement(
				"update accounts set balance = balance - ? where id = ?")) {

			queryRunner.setDouble(1, balance);
			queryRunner.setInt(2, accountId);

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while substracting balance from an account: " + e.getMessage());
		}
		return false;
	}
}
