package com.example.airbnb.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,USER,ADMIN_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
