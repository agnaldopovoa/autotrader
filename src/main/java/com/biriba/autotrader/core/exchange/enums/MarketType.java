package com.biriba.autotrader.core.exchange.enums;

public enum MarketType {
    SPOT("Spot"),
    FUTURE("Future");

	private String description;

    private MarketType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
