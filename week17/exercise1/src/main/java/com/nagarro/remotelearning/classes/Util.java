package com.nagarro.remotelearning.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {
	private Properties properties;

	public Util() {
		properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("config.properties");
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDatabaseUrl() {
		return properties.getProperty("db.url");
	}

	public String getDatabaseDriver() {
		return properties.getProperty("db.driver");
	}

	public String getDatabaseUsername() {
		return properties.getProperty("db.username");
	}

	public String getDatabasePassword() {
		return properties.getProperty("db.password");
	}
}
