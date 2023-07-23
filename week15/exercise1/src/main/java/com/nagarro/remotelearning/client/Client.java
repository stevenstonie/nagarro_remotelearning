package com.nagarro.remotelearning.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	@Override
	public void run() {
		int port = 8080;
		String host = "localhost";
		String requestUrl = "http://example.com/";

		try (Socket clientSocket = new Socket(host, port);
				PrintWriter dataForServer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inputFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()))) {

			dataForServer.println("GET" + " " + requestUrl + " " + "HTTP/2");
			dataForServer.println("Host: " + host);

			Thread.sleep(1000);

			String serverResponseLine;
			while ((serverResponseLine = inputFromServer.readLine()) != null) {
				System.out.println(serverResponseLine);
			}
		} catch (IOException e) {
			System.out.println("exception caught on Client.java, main(), client socket initialization, "
					+ e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("exception caught on Client.java, main(), thread sleep, " + e.getMessage());
		}
	}
}
