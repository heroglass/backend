package com.junhyeong.heroglass.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority{

    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }

}
