package com.sukhorukova.dev.httpserver.servlets;

import lombok.Data;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@Data
public class HttpServletRequestImpl implements HttpServletRequest  {
    private String authType;
    private Cookie[] cookies;
    private long dateHeader;
    private String header;
    private String method;
    private String pathInfo;
    private String pathTranslated;
    private String contextPath;
    private String queryString;
    private String remoteUser;
    private Principal userPrincipal;
    private String requestedSessionId;
    private String requestURI;
    private StringBuffer requestURL;
    private String servletPath;
    private HttpSession session;
    private boolean requestedSessionIdValid;
    private boolean requestedSessionIdFromCookie;
    private boolean requestedSessionIdFromUrl;
    private boolean requestedSessionIdFromURL;
    private Enumeration<String> headerNames;
    private Collection<Part> parts;
    private Enumeration<String> attributeNames;
    private String characterEncoding;
    private int contentLength;
    private long contentLengthLong;
    private String contentType;
    private ServletInputStream inputStream;
    private Enumeration<String> parameterNames;
    private Map<String, String[]> parameterMap;
    private String protocol;
    private String scheme;
    private String serverName;
    private int serverPort;
    private BufferedReader reader;
    private String remoteAddr;
    private String remoteHost;
    private Locale locale;
    private Enumeration<Locale> locales;
    private boolean secure;
    private int remotePort;
    private String localAddr;
    private String localName;
    private int localPort;
    private ServletContext servletContext;
    private boolean asyncStarted;
    private boolean asyncSupported;
    private AsyncContext asyncContext;
    private DispatcherType dispatcherType;

    @Override
    public long getDateHeader(String name) {
        return 0;
    }

    @Override
    public String getHeader(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return null;
    }

    @Override
    public int getIntHeader(String name) {
        return 0;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public HttpSession getSession(boolean create) {
        return null;
    }

    @Override
    public String changeSessionId() {
        return null;
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String username, String password) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        return null;
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

    }

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public String[] getParameterValues(String name) {
        return new String[0];
    }

    @Override
    public void setAttribute(String name, Object o) {

    }

    @Override
    public void removeAttribute(String name) {

    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    @Override
    public String getRealPath(String path) {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return requestedSessionIdFromURL;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return requestedSessionIdFromUrl;
    }
}
