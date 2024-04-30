package com.scamps.ClubService.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtConfig.getAuthHeaderName());

        if(header == null || !header.startsWith(JwtConfig.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(JwtConfig.getTokenPrefix(), "");
        RequestContextHolder.getRequestAttributes().setAttribute("token", token, 0);

        try {
            Claims claims = Jwts.parser().decryptWith(JwtConfig.getPassword()).build().parseEncryptedClaims(token).getPayload();
            String username = claims.getSubject();
            Object roles = claims.get("roles");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class).
                            readValue(roles.toString(), SimpleGrantedAuthority[].class)
            );

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> resp = Map.of("error", "Invalid token");
            response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(JwtConfig.getContentType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
