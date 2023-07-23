package com.nagarro.remotelearning.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nagarro.remotelearning.classes.ClientAccount;
import com.nagarro.remotelearning.classes.Pair;

public class ClientsAccountsRepository {
	private final Connection connection;

	public ClientsAccountsRepository(Connection connection) {
		this.connection = connection;
	}

	public boolean createClientsAccountsTable() {
		try (PreparedStatement queryRunner = connection.prepareStatement(new StringBuilder()
				.append("CREATE TABLE IF NOT EXISTS clients_accounts (")
				.append("id SERIAL PRIMARY KEY, ")
				.append("client_id INT, ")
				.append("account_id INT, ")
				.append("FOREIGN KEY (client_id) REFERENCES clients(id), ")
				.append("FOREIGN KEY (account_id) REFERENCES accounts(id));").toString())) {

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("SQLException caught while creating the clients_accounts table: " + e.getMessage());
		}
		return false;
	}

	public void linkClientWithAccount(Integer clientId, Integer accountId) {
		final String INSERT_CLIENTS_ACCOUNTS = "insert into clients_accounts (client_id, account_id) values (?, ?)";

		try (PreparedStatement queryRunner = connection.prepareStatement(INSERT_CLIENTS_ACCOUNTS)) {
			queryRunner.setInt(1, clientId);
			queryRunner.setInt(2, accountId);
			queryRunner.executeUpdate();
		} catch (SQLException e) {
			System.err.println("sqlexception caught while linking client with account: " + e.getMessage());
		}
	}

	public List<ClientAccount> getAllClientsAccounts() {
		try (PreparedStatement queryRunner = connection.prepareStatement("select * from clients_accounts;")) {

			ResultSet queryOutput = queryRunner.executeQuery();

			return returnClientsAccountsFromQuery(queryOutput);

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting all clients_accounts: " + e.getMessage());
		}
		return null;
	}

	public List<ClientAccount> getAllAccountsWithBalanceOver(Double balance) {
		try (PreparedStatement queryRunner = connection.prepareStatement(
				"SELECT clients.id as client_id, clients.username, clients.email, accounts.id as account_id, accounts.balance "
						+ "FROM clients "
						+ "JOIN clients_accounts ON clients.id = clients_accounts.client_id "
						+ "JOIN accounts ON clients_accounts.account_id = accounts.id "
						+ "where accounts.balance > ?")) {

			queryRunner.setDouble(1, balance);

			ResultSet queryOutput = queryRunner.executeQuery();

			return returnClientsAccountsFromQuery(queryOutput);

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting clients_accounts with balance over " + balance + ": "
					+ e.getMessage());
		}
		return null;
	}

	public Double getClientBalance(Integer clientId) {
		try (PreparedStatement queryRunner = connection.prepareStatement(
				"SELECT accounts.balance " +
						"FROM clients " +
						"JOIN clients_accounts ON clients.id = clients_accounts.client_id " +
						"JOIN accounts ON clients_accounts.account_id = accounts.id " +
						"where clients.id = ?")) {

			queryRunner.setInt(1, clientId);

			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next())
				return queryOutput.getDouble("balance");

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting client balance: " + e.getMessage());
		}

		return null;
	}

	public boolean removeAllClientsAndAccounts() {
		if (removeAllLinks()) {
			if (removeAllAccounts()) {
				if (removeAllClients()) {
					return true;
				}
			}
		}
		return false;
	}

	public String getDatabaseName() {
		try {
			return connection.getMetaData().getDatabaseProductName();

		} catch (Exception e) {
			System.err.println("Exception caught while getting database name: " + e.getMessage());
		}
		return null;
	}

	public List<Pair<String, List<String>>> getDatabaseTablesAndColumns() {
		List<Pair<String, List<String>>> tablesAndColumns = new ArrayList<>();

		try (PreparedStatement getTablesFromDatabaseQuery = connection
				.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");) {
			ResultSet queryOutput = getTablesFromDatabaseQuery.executeQuery();

			while (queryOutput.next()) {
				List<String> columns = new ArrayList<>();
				String tableName = queryOutput.getString("TABLE_NAME");

				PreparedStatement getColumnsFromTableQuery = connection.prepareStatement(
						"SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?");
				getColumnsFromTableQuery.setString(1, tableName);
				ResultSet columnsFromQuery = getColumnsFromTableQuery.executeQuery();

				while (columnsFromQuery.next()) {
					String columnName = columnsFromQuery.getString("COLUMN_NAME");
					String dataType = columnsFromQuery.getString("DATA_TYPE");

					columns.add(columnName + " (" + dataType + ")");
				}
				tablesAndColumns.add(new Pair<>(tableName, columns));

				columnsFromQuery.close();
			}

			return tablesAndColumns;
		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting database tables and columns: " + e.getMessage());
		}
		return null;
	}

	private boolean removeAllLinks() {
		try (PreparedStatement queryRunner = connection
				.prepareStatement("delete from clients_accounts where id > 0;")) {

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while removing all clients_accounts: " + e.getMessage());
		}
		return false;
	}

	private boolean removeAllAccounts() {
		try (PreparedStatement queryRunner = connection.prepareStatement("delete from accounts where id > 0;")) {

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while removing all accounts: " + e.getMessage());
		}
		return false;
	}

	private boolean removeAllClients() {
		try (PreparedStatement queryRunner = connection.prepareStatement("delete from clients where id > 0;")) {

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while removing all clients: " + e.getMessage());
		}
		return false;
	}

	private List<ClientAccount> returnClientsAccountsFromQuery(ResultSet queryOutput) {
		List<ClientAccount> clientsAccounts = new ArrayList<>();

		try {
			while (queryOutput.next()) {
				clientsAccounts.add(new ClientAccount(queryOutput.getInt("client_id"),
						queryOutput.getString("username"), queryOutput.getString("email"),
						queryOutput.getInt("account_id"), queryOutput.getDouble("balance")));
			}

			return clientsAccounts;
		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting all clients_accounts: " + e.getMessage());
		}

		return null;
	}
}
