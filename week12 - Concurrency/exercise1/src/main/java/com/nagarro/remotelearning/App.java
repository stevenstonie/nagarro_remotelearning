package com.nagarro.remotelearning;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Queue;

import com.nagarro.remotelearning.classes.Server;
import com.nagarro.remotelearning.classes.threads.Node;

public class App {
    public static void main(String[] args) {
        Server server = new Server();
        Thread serverThread = new Thread(server);
        Thread[] nodes = new Thread[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new Thread(new Node(server, i));
        }

        serverThread.start();

        for (Thread node : nodes) {
            node.start();
        }

        joinNodes(nodes);

        printLogsRepoIntoFile(server.getLogRepo());
    }

    private static void printLogsRepoIntoFile(Queue<String> logRepo) {
        try (RandomAccessFile logsRepoFile = createEmptyLogsRepoFile()) {
            while (!logRepo.isEmpty()) {
                try {
                    logsRepoFile.writeBytes(logRepo.poll() + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static RandomAccessFile createEmptyLogsRepoFile() {
        try {
            new File("logs_repo.txt").createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            return new RandomAccessFile("logs_repo.txt", "rw");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private static void joinNodes(Thread[] nodes) {
        try {
            for (Thread node : nodes) {
                node.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
