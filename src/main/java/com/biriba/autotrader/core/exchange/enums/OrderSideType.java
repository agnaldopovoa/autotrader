package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderSideType {
    BUY("BUY", "Buy"),
    SELL("SELL", "Sell");
    
    private String code;
	private String description;

    private OrderSideType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    @JsonCreator
    public static final OrderSideType getOrderSideTypeByCode(String code) {
        OrderSideType retVal = null;

        for (OrderSideType orderSide : OrderSideType.values()) {
            if (orderSide.getDescription().equalsIgnoreCase(code)) {
                retVal = orderSide;
            }
        }

        return retVal;
    }
    
}
