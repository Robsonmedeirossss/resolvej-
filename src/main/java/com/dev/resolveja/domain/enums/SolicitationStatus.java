package com.dev.resolveja.domain.enums;

import java.util.Set;

public enum SolicitationStatus {
    PENDING,
    CONFIRMED,
    REALIZED,
    CANCELED;

    private Set<SolicitationStatus> allowedTransictions;
    
    static {
        PENDING.allowedTransictions = Set.of(CONFIRMED, CANCELED);
        CONFIRMED.allowedTransictions = Set.of(REALIZED, CANCELED);
        REALIZED.allowedTransictions = Set.of();
        CANCELED.allowedTransictions = Set.of();
    }

    public boolean canTransiction(SolicitationStatus status) {
        return this.allowedTransictions.contains(status);
    }

    public boolean isRealized() {
        return this == REALIZED;
    }

    public String getAllowedTransictions() {
        return this.allowedTransictions.toString();
    }

    
}
