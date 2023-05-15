package com.biriba.autotrader.core.exchange.enums;

public enum NetworkType {
    MAINNET("MainNet"),
    TESTNET("TestNet");
    
    private String description;

    private NetworkType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
