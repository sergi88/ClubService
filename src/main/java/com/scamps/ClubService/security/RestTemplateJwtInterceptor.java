package com.scamps.ClubService.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;

@Component
public class RestTemplateJwtInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = (String) RequestContextHolder.getRequestAttributes().getAttribute("token", 0);

        if (token != null ) {
            HttpHeaders headers = request.getHeaders();
            headers.add(JwtConfig.getAuthHeaderName(), JwtConfig.getTokenPrefix() + token);
        }

        return execution.execute(request, body);
    }
}
