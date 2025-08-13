package org.lwx.learnspring.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lwx.learnspring.config.AuthorizeMatchersProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthorizeMatchersProperties properties;

    @Value("${jwt.key}")
    private String secretKey;

    private byte[] secretKeyBytes;

    @PostConstruct
    public void init() {
        secretKeyBytes = secretKey.getBytes();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (shouldSkipAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        try{
            if(token == null || StringUtils.isEmpty(token)){
                filterChain.doFilter(request, response);
            }
            JWT jwt = JWTUtil.parseToken(token);
            boolean isValidate = jwt.setKey(secretKeyBytes).validate(0);
            if (!isValidate) {
                log.error("JwtValidationFilter error: token is invalid");
                throw new Exception("token 失效");
            }
            JSONObject payload = jwt.getPayloads();
            String username = payload.getStr("username");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }
    private boolean shouldSkipAuthentication(HttpServletRequest request) {
        // 定义不需要认证的路径
        String path = request.getRequestURI();

        for (String url : properties.getPermit_urls()) {
            if (path.startsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
