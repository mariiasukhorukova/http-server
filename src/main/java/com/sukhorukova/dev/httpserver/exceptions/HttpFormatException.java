package com.sukhorukova.dev.httpserver.exceptions;

import lombok.extern.log4j.Log4j;

@Log4j
public class HttpFormatException extends Exception {
    public HttpFormatException(String message) {
        super(message);
    }
}
