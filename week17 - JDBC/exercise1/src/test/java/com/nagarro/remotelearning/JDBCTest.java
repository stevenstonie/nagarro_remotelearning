package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nagarro.remotelearning.classes.ClientAccount;
import com.nagarro.remotelearning.classes.DBUtils;
import com.nagarro.remotelearning.classes.Pair;
import com.nagarro.remotelearning.classes.Triple;
import com.nagarro.remotelearning.repository.AccountsRepository;
import com.nagarro.remotelearning.repository.ClientsAccountsRepository;
import com.nagarro.remotelearning.repository.ClientsRepository;

public class JDBCTest {
	private static Connection connection;
	private static ClientsRepository clientsRepository;
	private static AccountsRepository accountsRepository;
	private static ClientsAccountsRepository clientsAccountsRepository;

	@BeforeAll
	public static void setupConnectionAndRepos() {
		initializeConnectionAndRepos();
	}

	@BeforeEach
	public void setup() {
		clientsAccountsRepository.removeAllClientsAndAccounts();

		List<Triple<String, String, Double>> clientsAccounts = List.of(
				new Triple<String, String, Double>("client1", "email1", 100.0),
				new Triple<String, String, Double>("client2", "email2", 200.0),
				new Triple<String, String, Double>("client3", "email3", 300.0));

		addClientsAndAccounts(clientsAccounts);
	}

	@Test
	public void testGetAllClients() {
		List<ClientAccount> clients = clientsRepository.getAllClients();

		assertEquals(clients.size(), 3);
		assertEquals(clients.get(0).getUsername(), "client1");
		assertEquals(clients.get(1).getEmail(), "email2");
		assertEquals(clients.get(2).getBalance(), null);
	}

	@Test
	public void testGetAccountBalanceFromAClient() {
		Double balance = clientsAccountsRepository.getClientBalance(clientsRepository.getClientIdByUsername("client2"));

		assertEquals(balance, 200.0);
	}

	@Test
	public void testGetAccountBalanceFromAnInexistentClient() {
		Double balance = clientsAccountsRepository.getClientBalance(100);

		assertNull(balance);
	}

	@Test
	public void testGetAccountsWithBalanceOverAValue() {
		List<ClientAccount> clientsAccounts = clientsAccountsRepository.getAllAccountsWithBalanceOver(199.);

		assertEquals(clientsAccounts.size(), 2);
		assertEquals(clientsAccounts.get(0).getUsername(), "client2");
		assertEquals(clientsAccounts.get(1).getBalance(), 300.0);
	}

	@Test
	public void testGetNoAccountsWithBalanceOver9999() {
		List<ClientAccount> clientsAccounts = clientsAccountsRepository.getAllAccountsWithBalanceOver(9999.);

		assertEquals(clientsAccounts.size(), 0);
	}

	@Test
	public void testAddBalanceToAccount() {
		Integer clientId = clientsRepository.getClientIdByUsername("client1");

		accountsRepository.addBalanceToClientAccount(clientId, 15.38);

		assertEquals(clientsAccountsRepository.getClientBalance(clientId), 115.38);
	}

	@Test
	public void testSubstractBalanceFromAccount() {
		Integer clientId = clientsRepository.getClientIdByUsername("client3");

		accountsRepository.substractBalanceFromClientAccount(clientId, 15.38);

		assertEquals(clientsAccountsRepository.getClientBalance(clientId), 284.62);
	}

	@Test
	public void testPrintAllDatabaseData() {
		System.out.println(clientsAccountsRepository.getDatabaseName());

		List<Pair<String, List<String>>> tablesData = clientsAccountsRepository.getDatabaseTablesAndColumns();

		for (var tableData : tablesData) {
			System.out.println(tableData.getFirst());
			System.out.println(tableData.getSecond());
		}
	}

	//

	private static void initializeConnectionAndRepos() {
		connection = DBUtils.getDbConnection();
		clientsRepository = new ClientsRepository(connection);
		accountsRepository = new AccountsRepository(connection);
		clientsAccountsRepository = new ClientsAccountsRepository(connection);

		clientsRepository.createClientsTable();
		accountsRepository.createAccountsTable();
		clientsAccountsRepository.createClientsAccountsTable();
	}

	private static void addClientsAndAccounts(List<Triple<String, String, Double>> clientsAccounts) {
		for (var clientAccount : clientsAccounts) {
			String clientUsername = clientAccount.getFirst();
			String clientEmail = clientAccount.getSecond();
			Double clientBalance = clientAccount.getThird();

			clientsRepository.insertClient(clientUsername, clientEmail);

			Integer currentClientId = clientsRepository.getClientIdByUsername(clientUsername);

			accountsRepository.insertAccount(clientBalance, currentClientId);

			clientsAccountsRepository.linkClientWithAccount(
					currentClientId,
					accountsRepository.getAccountIdByClientId(currentClientId));
		}
	}
}
