package com.biriba.autotrader.core.exchange.enums;

public enum FilterType {
    PRICE_FILTER("Price"),
    PERCENT_PRICE("Percent Price"),
    LOT_SIZE("Lot size"),
    MARKET_LOT_SIZE("Market Lot Size"),
    MAX_NUM_ORDERS("Max Num Orders"),
    MAX_NUM_ALGO_ORDERS("Max Num Algo Orders"),
    MIN_NOTIONAL("Min Notional");
    
	private String description;

    private FilterType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
