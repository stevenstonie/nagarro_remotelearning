package com.nagarro.remotelearning.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;

public class Server implements Runnable {
    private int port;
    private CountDownLatch latch;

    public Server(int port, CountDownLatch latch) {
        this.port = port;
        this.latch = latch;
    }

    @Override
    public void run() {
        start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server is listening on port " + port);
            latch.countDown();

            while (true) { // blocked -> see (1)
                try (Socket clientSocket = serverSocket.accept(); // (1) blocking the while until a client actually connects
                        PrintWriter writeToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader inputFromClient = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.println("new client connected: " + clientSocket.getInetAddress());

                    String clientRequestedUrl = getClientRequestedUrl(inputFromClient);

                    SendUrlContentsToClient(clientRequestedUrl, writeToClient);
                }
            }
        } catch (IOException e) {
            System.out
                    .println("exception caught on Server.java, server socket initialization" + e.getMessage());
        }
    }

    private void SendUrlContentsToClient(String clientRequestedUrl, PrintWriter writeToClient) throws IOException {
        if (clientRequestedUrl != null) {
            URL url = new URL(clientRequestedUrl);
            URLConnection connection = url.openConnection();
            BufferedReader urlReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            writeToClient.println("~~~~~~~~~~~~~~~~");
            writeToClient.println("HTTP/2 200 OK");
            writeToClient.println("~~~~~~~~~~~~~~~~");

            String fetchedUrlContent;
            while ((fetchedUrlContent = urlReader.readLine()) != null) {
                writeToClient.println(fetchedUrlContent);
            }
        }
    }

    private String getClientRequestedUrl(BufferedReader inputFromClient) {
        String clientRequestString;
        String clientRequestedUrl = null;

        try {
            while ((clientRequestString = inputFromClient.readLine()) != null) {
                if (clientRequestString.startsWith("GET")) {
                    clientRequestedUrl = clientRequestString.split(" ")[1];
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "the client disconnected before the server finished processing the request: " + e.getMessage());
        }
        return clientRequestedUrl;
    }

}
