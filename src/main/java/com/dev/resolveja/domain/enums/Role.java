package com.dev.resolveja.domain.enums;

public enum Role {
    ADMIN,
    CUSTOMER,
    PROVIDER;

    Boolean isAdmin() {
        return this == ADMIN;
    }

    Boolean isCustomer() {
        return this == CUSTOMER;
    }

    Boolean isProvider() {
        return this == PROVIDER;
    }
}
