package com.spring.resturant.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;
    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }


}
