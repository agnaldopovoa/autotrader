package com.biriba.autotrader.core.exchange.enums;

public enum TimeInForceType {
    GTC("Good Till Cancel"),
    IOC ("Immediate or Cancel"),
    FOK ("Fill or Kill"),
    GTX("Good Till Crossing");
    
	private String description;

    private TimeInForceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
