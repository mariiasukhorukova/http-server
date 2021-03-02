package com.sukhorukova.dev.httpserver.servletscontainer;

import com.sukhorukova.dev.httpserver.parsers.HttpRequestParser;
import com.sukhorukova.dev.httpserver.parsers.YamlParser;
import com.sukhorukova.dev.httpserver.servlets.HttpServletRequestImpl;
import com.sukhorukova.dev.httpserver.servlets.HttpServletResponseImpl;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static com.sukhorukova.dev.httpserver.Constants.*;

@Log4j
public class ServletsContainer {
    private final YamlParser yamlParser;

    public ServletsContainer() {
        this.yamlParser = new YamlParser();
        yamlParser.parse("web.yml");
    }

    public void processRequest(String request, BufferedOutputStream out) throws Exception {
        HttpRequestParser httpRequestParser = new HttpRequestParser();

        httpRequestParser.parseRequest(request);
        httpRequestParser.logRequestData();

        String servletName = getServletClassName(yamlParser, httpRequestParser.getUrl());
        if (servletName == null) {
            writeMessageToOutputStream(out, HTTP_BAD_URI);
        } else {
            Class<? extends HttpServlet> servletClazz = (Class<? extends HttpServlet>) Class.forName(servletName);
            Object servlet = servletClazz.getConstructor().newInstance();

            Method method = servletClazz.getDeclaredMethod(getMethodName(httpRequestParser.getHttpMethod()),
                    HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            HttpServletRequestImpl httpServletRequest = new HttpServletRequestImpl();
            HttpServletResponseImpl httpServletResponse = new HttpServletResponseImpl(out);

            method.invoke(servlet, httpServletRequest, httpServletResponse);

            String status = HTTP +
                    SPACE +
                    httpServletResponse.getStatus() +
                    SPACE +
                    OK +
                    NEW_LINE;
            String contentType = CONTENT_TYPE +
                    COLON +
                    SPACE +
                    httpServletResponse.getContentType() +
                    NEW_LINE;
            String date = DATE +
                    COLON +
                    SPACE +
                    new Date() +
                    NEW_LINE;

            out.write(status.getBytes(), 0, status.length());
            out.write(contentType.getBytes(), 0, contentType.length());
            out.write(date.getBytes(), 0, date.length());
            out.write(NEW_LINE.getBytes(), 0, NEW_LINE.length());
            out.flush();

            if (httpServletResponse.getWriter() != null) {
                httpServletResponse.getWriter().flush();
            }
        }
    }

    public void writeMessageToOutputStream(BufferedOutputStream out, String message) throws IOException {
        out.write(message.getBytes(), 0, message.length());
        out.flush();
    }

    private String getMethodName(String httpMethod) {
        switch (httpMethod) {
            case GET: {
                return DO_GET;
            }
            case POST: {
                return DO_POST;
            }
            case PUT: {
                return DO_PUT;
            }
            case PATCH: {
                return DO_PATCH;
            }
            case DELETE: {
                return DO_DELETE;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + httpMethod);
        }
    }

    private String getServletClassName(YamlParser yamlParser, String url) {

        Object servletMappingObject = yamlParser.getResultMap().get(SERVLET_MAPPING);

        if (servletMappingObject instanceof ArrayList) {
            ArrayList<?> servletMappingList = ((ArrayList) servletMappingObject);
            Optional<String> result = servletMappingList.stream()
                    .filter(obj -> obj instanceof HashMap)
                    .map(obj -> (HashMap<String, String>) obj)
                    .filter(mapping -> mapping.get(URL).equals(url))
                    .map(mapping -> mapping.get(CLASS))
                    .findFirst();

            if (result.isPresent()) {
                return result.get();
            }
        }
        return null;
    }
}
