package com.nagarro.remotelearning.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;

	private DBUtils() {
		Util configReader = new Util();

		try {
			Class.forName(configReader.getDatabaseDriver());
			connection = DriverManager.getConnection(configReader.getDatabaseUrl(),
					configReader.getDatabaseUsername(), configReader.getDatabasePassword());

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("exception caught while connecting to the database: " + e.getMessage());
		}
	}

	public static synchronized Connection getDbConnection() {
		if (connection == null) {
			new DBUtils();
		}
		return connection;
	}
}
