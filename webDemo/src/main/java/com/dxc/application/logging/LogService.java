package com.dxc.application.logging;

import com.dxc.application.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        if (body != null) {
            stringBuilder.append("Request Body=[").append(JsonUtil.toJsonString(body)).append("]");
        }
        log.info(stringBuilder.toString());
    }

    public void logResponse(HttpServletRequest request, Object body) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(request.getMethod()).append("] ");
        stringBuilder.append("path=[").append(request.getRequestURI()).append("] ");
        if (body != null) {
            stringBuilder.append("Response Body=[").append(JsonUtil.toJsonString(body)).append("]");
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
}
