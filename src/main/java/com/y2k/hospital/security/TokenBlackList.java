package com.y2k.hospital.security;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlackList {

    private final Set<String> blockedTokens = new HashSet<>();

    public void addToken(String token) {
        blockedTokens.add(token);
    }

    public boolean isTokenBlocked(String token) {
        return blockedTokens.contains(token);
    }
}