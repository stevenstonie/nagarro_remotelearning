package com.nagarro.remotelearning.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.remotelearning.classes.ClientAccount;

public class ClientsRepository {
	private final Connection connection;

	public ClientsRepository(Connection connection) {
		this.connection = connection;
	}

	public boolean createClientsTable() {
		try (PreparedStatement queryRunner = connection.prepareStatement("create table if not exists clients ("
				+ "id serial primary key, "
				+ "username varchar(50) not null unique, "
				+ "email varchar(50) not null unique);")) {

			queryRunner.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.err.println("SQLException caught while creating the clients table: " + e.getMessage());
		}
		return false;
	}

	public boolean insertClient(String username, String email) {
		try (PreparedStatement queryRunner = connection
				.prepareStatement("insert into clients (username, email) values (?, ?)")) {

			queryRunner.setString(1, username);
			queryRunner.setString(2, email);

			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while inserting client: " + e.getMessage());
		}
		return false;
	}

	public List<ClientAccount> getAllClients() {
		try (PreparedStatement queryRunner = connection.prepareStatement("select * from clients;")) {

			ResultSet queryOutput = queryRunner.executeQuery();

			return returnClientsFromQueryOutput(queryOutput);

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting all clients: " + e.getMessage());
		}
		return null;
	}

	public boolean updateClient(Integer id, String username, String email) {
		try (PreparedStatement queryRunner = connection.prepareStatement(
				"update clients set username = ?, email = ? where id = ?")) {

			queryRunner.setString(1, username);
			queryRunner.setString(2, email);
			queryRunner.setInt(3, id);
			queryRunner.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.err.println("sqlexception caught while updating client: " + e.getMessage());
		}
		return false;
	}

	public ClientAccount getClientById(int id) {
		final String SELECT_BY_ID = "select * from clients where id = ?";

		try (PreparedStatement queryRunner = connection.prepareStatement(SELECT_BY_ID)) {
			queryRunner.setInt(1, id);
			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next()) {
				return new ClientAccount(queryOutput.getInt("id"), queryOutput.getString("username"),
						queryOutput.getString("email"), null, null);
			}

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting client by id: " + e.getMessage());
		}
		return null;
	}

	public ClientAccount getClientByUsername(String username) {
		final String SELECT_BY_USERNAME = "select * from clients where username = ?";

		try (PreparedStatement queryRunner = connection.prepareStatement(SELECT_BY_USERNAME)) {
			queryRunner.setString(1, username);
			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next()) {
				return new ClientAccount(queryOutput.getInt("id"), queryOutput.getString("username"),
						queryOutput.getString("email"), null, null);
			}

		} catch (SQLException e) {
			System.err.println("sqlexception caught while getting client by username: " + e.getMessage());
		}
		return null;
	}

	public Integer getClientIdByUsername(String username) {
		try (PreparedStatement queryRunner = connection.prepareStatement("select id from clients where username = ?")) {
			if (username == null || username.isEmpty()) {
				throw new IllegalArgumentException("Username parameter cannot be null or empty");
			}

			queryRunner.setString(1, username);
			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next()) {
				return queryOutput.getInt(1);
			}

		} catch (SQLException e) {
			System.err.println("SQLException caught while getting client id by username: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err
					.println("IllegalArgumentException caught while getting client id by username: " + e.getMessage());
		}
		return null;
	}

	private List<ClientAccount> returnClientsFromQueryOutput(ResultSet queryOutput) {
		List<ClientAccount> clients = new ArrayList<>();

		try {
			while (queryOutput.next()) {
				clients.add(new ClientAccount(queryOutput.getInt("id"), queryOutput.getString("username"),
						queryOutput.getString("email"), null, null));
			}
		} catch (SQLException e) {
			System.err.println("SQLException caught while returning clients from query output: " + e.getMessage());
		}

		return clients;
	}
}
