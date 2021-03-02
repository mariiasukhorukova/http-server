package com.sukhorukova.dev.httpserver;

import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class Main {
    public static void main(String[] args) {
        try {
            HttpServer httpServer = new HttpServer(8181);
            httpServer.process();
        } catch (IOException e) {
            log.error("An error occurred while the web server working: " + e);
        }
    }
}
