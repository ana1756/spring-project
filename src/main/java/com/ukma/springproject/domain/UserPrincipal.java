package com.ukma.springproject.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserPrincipal extends org.springframework.security.core.userdetails.User  {

    private User user;

    public UserPrincipal(User user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
