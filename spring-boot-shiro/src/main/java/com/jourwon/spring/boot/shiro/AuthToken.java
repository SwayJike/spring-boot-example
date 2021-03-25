package com.jourwon.spring.boot.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author JourWon
 * @date 2021/3/25
 */
public class AuthToken implements AuthenticationToken {

    private String token;

    public AuthToken(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
