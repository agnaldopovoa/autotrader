package com.biriba.autotrader.core.exchange.enums;

public enum OrderType {
    LIMIT("Limit"),
    MARKET("Market"),
    STOP("Stop"),
    STOP_MARKET("Stop Market"),
    TAKE_PROFIT("Take Profit"),
    TAKE_PROFIT_MARKET("Take Profit Market"),
    TRAILING_STOP_MARKET("Traling Stop Market");
    
	private String description;

    private OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
