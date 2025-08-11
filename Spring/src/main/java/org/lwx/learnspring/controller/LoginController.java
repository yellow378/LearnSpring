package org.lwx.learnspring.controller;

import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.PostConstruct;
import org.lwx.learnspring.api.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.key}")
    private String key;

    private byte[] keyBytes;

    @PostConstruct
    public void init() {
        keyBytes = key.getBytes();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String,Object> payload = new HashMap<>();
        payload.put("username",loginRequest.getUsername());
        payload.put("password",loginRequest.getPassword());
        String token = JWTUtil.createToken(payload,keyBytes);

        Map<String,Object> result = new HashMap<>();
        result.put("token",token);

        return ResponseEntity.ok(JSONObject.toJSONString(result));
    }
}
