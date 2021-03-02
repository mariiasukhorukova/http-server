package com.sukhorukova.dev.httpserver.util;

import java.util.Locale;

public class Constants {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String PATCH = "PATCH";
    public static final String DELETE = "DELETE";

    public static final String DO_GET = "doGet";
    public static final String DO_POST = "doPost";
    public static final String DO_PUT = "doPut";
    public static final String DO_PATCH = "doPatch";
    public static final String DO_DELETE = "doDelete";

    public static final String HTTP = "HTTP/1.1";
    public static final String HTTP_BAD_URI = HTTP + " 404";
    public static final String OK = "OK";
    public static final String CONTENT_TYPE = "content-type";
    public static final String DATE = "Date";
    public static final String NEW_LINE = "\n";
    public static final String SPACE = " ";
    public static final String COLON = ":";
    public static final String SLASH = "/";

    public static final Locale DEFAULT_LOCALE = Locale.getDefault();
    public static final String SERVLET_MAPPING = "servlet-mapping";
    public static final String URL = "url";
    public static final String CLASS = "class";
}
