package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatusType {
    NEW("NEW", "New"),
    PARTIALLY_FILLED("PARTIALLY_FILLED", "Partially Filled"),
    FILLED("FILLED", "Filled"),
    CANCELED("CANCELED", "Canceled"),
    REJECTED("REJECTED", "Rejected"),
    EXPIRED("EXPIRED", "Expired");
    
    private String code;
	private String description;

    private OrderStatusType(String code, String description) {
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
    public static final OrderStatusType getOrderStatusTypeByCode(String code) {
        OrderStatusType retVal = null;

        for (OrderStatusType orderStatus: OrderStatusType.values()) {
            if (orderStatus.getDescription().equalsIgnoreCase(code)) {
                retVal = orderStatus;
            }
        }

        return retVal;
    }
}
