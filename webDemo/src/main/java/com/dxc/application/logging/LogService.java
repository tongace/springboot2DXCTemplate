package com.dxc.application.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class LogService {
    public void logRequest(HttpServletRequest request, Object body) {
        final StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParameterMap(request);
        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(request.getMethod()).append("] ");
        stringBuilder.append("path=[").append(request.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(request)).append("] ");
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        if (body != null) {
            stringBuilder.append("Request Body=[").append(body).append("]");
        }
        log.info(stringBuilder.toString());
    }

    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(request.getMethod()).append("] ");
        stringBuilder.append("path=[").append(request.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(response)).append("] ");
        if (body != null) {
            stringBuilder.append("Response Body=[").append(body).append("]");
        }
        log.info(stringBuilder.toString());
    }

    private Map<String, String> buildParameterMap(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<>();
        String key;
        String value;
        while (request.getParameterNames().hasMoreElements()) {
            key = request.getParameterNames().nextElement();
            value = request.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }
}
