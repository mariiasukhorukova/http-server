package com.sukhorukova.dev.httpserver.parsers;

import com.sukhorukova.dev.httpserver.exceptions.HttpFormatException;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import static com.sukhorukova.dev.httpserver.Constants.*;

@Log4j
@Data
public class HttpRequestParser {

    private String httpMethod;
    private String url;
    private Map<String, String> requestHeaders;
    private StringBuffer messageBody;

    public HttpRequestParser() {
        requestHeaders = new HashMap<String, String>();
        messageBody = new StringBuffer();
    }

    public void parseRequest(String request) throws IOException, HttpFormatException {
        BufferedReader reader = new BufferedReader(new StringReader(request));

        String typeAndQuery = reader.readLine();

        if (typeAndQuery != null) {
            int startIndex = typeAndQuery.indexOf(SLASH);
            int endIndex = typeAndQuery.indexOf(SPACE, startIndex);
            this.httpMethod = typeAndQuery.substring(0, startIndex - 1);
            this.url = typeAndQuery.substring(startIndex, endIndex);
            String header = reader.readLine();
            if (header != null) {
                while (header.length() > 0) {
                    appendHeaderParameter(header);
                    header = reader.readLine();
                }

                String bodyLine = reader.readLine();
                while (bodyLine != null) {
                    appendMessageBody(bodyLine);
                    bodyLine = reader.readLine();
                }
            }
        }
    }

    public String getHeaderParam(String headerName) {
        return requestHeaders.get(headerName);
    }

    public void logRequestData() {
        log.info("Request from client ---------------->");

        if (this.httpMethod != null) {
            log.info(this.httpMethod + " " + this.url);
            log.info("Request Headers: ");
            this.requestHeaders.keySet().forEach(header -> {
                log.info(header + COLON + SPACE + this.getHeaderParam(header));
            });
            log.info("Request Body");
            log.info(this.getMessageBody().toString());
        }
    }

    private void appendHeaderParameter(String header) throws HttpFormatException {
        int index = header.indexOf(COLON);
        if (index == -1) {
            throw new HttpFormatException("Invalid Header Parameter: " + header);
        }
        requestHeaders.put(header.substring(0, index), header.substring(index + 1));
    }

    private void appendMessageBody(String bodyLine) {
        messageBody.append(bodyLine).append("\r\n");
    }
}
