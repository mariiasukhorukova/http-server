package com.sukhorukova.dev.httpserver.servlets;

import lombok.Data;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import static com.sukhorukova.dev.httpserver.Constants.DEFAULT_LOCALE;


@Data
public class HttpServletResponseImpl implements HttpServletResponse {
    private PrintWriter writer;
    private OutputStream outputBuffer;
    private String characterEncoding;
    private String contentType;
    private int status;
    private ServletOutputStream outputStream;
    private int contentLength;
    private long contentLengthLong;
    private int bufferSize;
    private boolean committed;
    private Locale locale;
    private Map<String, String> headers;
    private List<Cookie> cookies = new ArrayList<>();

    public HttpServletResponseImpl(OutputStream outputStream) {
        this.outputBuffer = outputStream;
        this.writer = new PrintWriter(outputStream);
    }

    @Override
    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public boolean containsHeader(String name) {
        return headers.containsKey(name);
    }

    @Override
    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        this.headers.put(name, String.valueOf(value));
    }

    @Override
    public void addIntHeader(String name, int value) {
        this.headers.put(name, String.valueOf(value));
    }

    @Override
    public void setStatus(int sc, String sm) {

    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return headers.keySet();
    }

    @Override
    public void flushBuffer() throws IOException {
    }

    @Override
    public void resetBuffer() {
    }

    @Override
    public void reset() {
        recycle();
    }


    @Override
    public String encodeURL(String url) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String url) {
        return null;
    }

    @Override
    public String encodeUrl(String url) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {

    }

    @Override
    public void sendError(int sc) throws IOException {

    }

    @Override
    public void sendRedirect(String location) throws IOException {

    }

    @Override
    public void setDateHeader(String name, long date) {

    }

    @Override
    public void addDateHeader(String name, long date) {

    }

    @Override
    public void setHeader(String name, String value) {

    }

    private void recycle() {
        contentType = null;
        locale = DEFAULT_LOCALE;
        characterEncoding = null;
        contentLength = -1;
        status = 200;
        committed = false;
        headers.clear();
    }
}
