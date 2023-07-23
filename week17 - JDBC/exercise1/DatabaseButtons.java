package com.nagarro.remotelearning.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DatabaseButtons implements ActionListener {
	Connection connection;

	public DatabaseButtons(Connection connection) {
		this.connection = connection;
	}

	public void addButtonsForTheQueries() {
		JFrame frame = new JFrame("database scenarios");
		JPanel panel = new JPanel();

		JButton button1 = new JButton("query_1");
		JButton button2 = new JButton("query_2");
		JButton button3 = new JButton("query_3");

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);

		panel.add(button1);
		panel.add(button2);
		panel.add(button3);

		frame.add(panel);
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		if (connection != null) {
			try {
				if ("query_1".equals(command)) {
					runQuery1();

				} else if ("query_2".equals(command)) {
					String username = JOptionPane.showInputDialog("enter the username of the client: ");
					if (username != null && !username.trim().isEmpty()) {
						runQuery2(username);
					} else {
						System.out.println("operation cancelled");
					}

				} else if ("query_3".equals(command)) {
					runQuery3();
				}
			} catch (SQLException e) {
				System.out.println("sqlexception caught while executing query: " + e.getMessage());
			}
		}
	}

	private void runQuery1() throws SQLException {
		String selectAllClientsQuery = "select * from clients";
		try (PreparedStatement queryRunner = connection.prepareStatement(selectAllClientsQuery)) {
			ResultSet queryOutput = queryRunner.executeQuery();

			while (queryOutput.next()) {
				System.out
						.println("client id: " + queryOutput.getInt("id") + ", username: "
								+ queryOutput.getString("username"));
			}
		}
	}

	private void runQuery2(String username) throws SQLException {
		String getBalanceFromXQuery = "select balance from clients where username = ?";
		try (PreparedStatement queryRunner = connection.prepareStatement(getBalanceFromXQuery)) {
			queryRunner.setString(1, username);
			ResultSet queryOutput = queryRunner.executeQuery();

			if (queryOutput.next()) {
				System.out.println("balance for the client named '" + username + "': "
						+ queryOutput.getDouble("balance"));
			} else {
				System.out.println("no client was found with the username '" + username + "'");
			}
		}
	}

	private void runQuery3() throws SQLException {
		Double balanceThreshold = 100000;
		String selectAllWhereXQuery = "select * from clients where balance > ?";
		try (PreparedStatement queryRunner = connection.prepareStatement(selectAllWhereXQuery)) {
			queryRunner.setDouble(1, balanceThreshold);
			ResultSet queryOutput = queryRunner.executeQuery();

			System.out.println("clients with balance over " + balanceThreshold + ":");
			while (queryOutput.next()) {
				System.out.println(
						"client id: " + queryOutput.getInt("id") + ", username: " + queryOutput.getString("username"));
			}
		}
	}
}
