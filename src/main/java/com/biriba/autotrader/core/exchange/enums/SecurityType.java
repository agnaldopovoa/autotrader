package com.biriba.autotrader.core.exchange.enums;

public enum SecurityType {
    NONE("None"),
    API_KEY("Api Key"),
    SIGNATURE("Signature");

    private String description;
    
    private SecurityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
