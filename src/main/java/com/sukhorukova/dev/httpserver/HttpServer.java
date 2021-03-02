package com.sukhorukova.dev.httpserver;

import com.sukhorukova.dev.httpserver.servletscontainer.ServletsContainer;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j
public class HttpServer {
    private final ServerSocket serverSocket;
    private final ServletsContainer servletsContainer;


    public HttpServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        log.info("Server is started!");
        this.servletsContainer = new ServletsContainer();
    }

    public void process() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(() -> handleConnection(clientSocket));
            }
        } catch (IOException e) {
            log.error("An error occurred: " + e);
        } finally {
            log.info("Closing client...");
            executorService.shutdown();
        }
    }

    private void handleConnection(Socket clientSocket) {
        log.debug("Client connected: " + clientSocket.getInetAddress() + " " + clientSocket.getPort());
        try (BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())) {
            String request = readMessageFromInputStream(in);

            this.servletsContainer.processRequest(request, out);
        } catch (Exception e) {
            log.error("An error occurred during handling the message", e);
        }
    }

    private String readMessageFromInputStream(BufferedInputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int dataLength;
        String message = "";
        while ((dataLength = in.read(buffer)) != -1) {

            StringBuilder sb = new StringBuilder(dataLength);
            for (int i = 0; i < dataLength; i++) {
                sb.append((char) buffer[i]);
            }

            int available = in.available();
            if (available == 0) {
                message = sb.toString();
                break;
            }
        }
        return message;
    }
}
