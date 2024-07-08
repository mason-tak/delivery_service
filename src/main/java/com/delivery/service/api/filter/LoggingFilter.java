package com.delivery.service.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        long startTime = System.currentTimeMillis();
        chain.doFilter(req, res);
        long end = System.currentTimeMillis();

        Enumeration<String> headerNames = req.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();
        String method = req.getMethod();
        String uri = req.getRequestURI();
        String requestBody = new String(req.getContentAsByteArray());

        headerNames.asIterator().forEachRemaining(headerKey -> {
            String headerValue = req.getHeader(headerKey);

            headerValues.append(headerKey).append(" : ").append(headerValue).append(" , ");
        });

        log.info("""
                        |
                        | [REQUEST] : {} {} ({})
                        | >> CLIENT_IP : {}
                        | >> HEADERS : [{}]
                        | >> REQUEST_PARAM : {}
                        | >> REQUEST_BODY : {}
                        """,
                method, uri, (end - startTime) / 1000.0,
                getClientIp(req),
                headerValues,
                getRequestParams(req),
                requestBody
        );

        StringBuilder responseValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            String headerValue = res.getHeader(headerKey);
            responseValues.append(headerKey).append(" : ").append(headerValue).append(" , ");
        });

        log.info("""
                        |
                        | [RESPONSE] : {} {} [{}] ({})
                        | >> CLIENT_IP : {}
                        | >> HEADERS : [{}]
                        | >> REQUEST_PARAM : {}
                        | >> REQUEST_BODY : {}
                        """,
                method, uri, res.getStatus(), (end - startTime) / 1000.0,
                getClientIp(req),
                headerValues,
                getRequestParams(req),
                requestBody
        );

        res.copyBodyToResponse();
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }

        return xfHeader.split(",")[0];
    }

    private String getRequestParams(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return parameterMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));
    }
}
