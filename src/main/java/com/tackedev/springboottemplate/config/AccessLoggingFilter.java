package com.tackedev.springboottemplate.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class AccessLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long processTime = System.currentTimeMillis() - startTime;

        log.debug("Request {} {} {} {} headers=[{}] parameters=[{}] body={}",
                requestWrapper::getRemoteAddr,
                requestWrapper::getProtocol,
                requestWrapper::getMethod,
                requestWrapper::getServletPath,
                () -> getHeaderAsString(requestWrapper),
                () -> getParametersAsString(requestWrapper),
                () -> getBodyAsString(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding()));
        log.debug("Response processingTime={} status={} {} body={}",
                () -> processTime,
                responseWrapper::getStatus,
                () -> HttpStatus.valueOf(responseWrapper.getStatus()).getReasonPhrase(),
                () -> getBodyAsString(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding()));

        responseWrapper.copyBodyToResponse();
    }

    private String getHeaderAsString(ContentCachingRequestWrapper requestWrapper) {
        var headerNames = requestWrapper.getHeaderNames();
        List<String> headers = new ArrayList<>();
        while (headerNames.hasMoreElements()) {
            var headerName = headerNames.nextElement();
            headers.add(headerName + "=" + requestWrapper.getHeader(headerName));
        }
        return StringUtils.collectionToCommaDelimitedString(headers);
    }

    private String getParametersAsString(ContentCachingRequestWrapper requestWrapper) {
        var parametersArray = requestWrapper.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + StringUtils.arrayToCommaDelimitedString(entry.getValue()))
                .toArray();
        return StringUtils.arrayToCommaDelimitedString(parametersArray);
    }

    private String getBodyAsString(byte[] contentAsByteArray, String charset) {
        try {
            return StringUtils.trimAllWhitespace(new String(contentAsByteArray, charset));
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported request charset", e);
        }
        return "";
    }

}
