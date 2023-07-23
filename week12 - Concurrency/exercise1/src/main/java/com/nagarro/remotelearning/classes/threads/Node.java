package com.nagarro.remotelearning.classes.threads;

import com.nagarro.remotelearning.classes.Server;

public class Node implements Runnable {
	private final Server server;
	private final int id;

	public Node(Server server, int id) {
		this.server = server;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			server.addLog("log " + i + " (node " + id + ")");
		}
	}
}
