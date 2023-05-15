package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RateLimitersType {
    REQUEST_WEIGHT("REQUEST_WEIGHT","Request Weight"),
    ORDERS("ORDERS","Orders");

    private String code;
	private String description;

    private RateLimitersType(String code, String description) {
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
    public static final RateLimitersType getRateLimitersTypeByCode(String code) {
        RateLimitersType retVal = null;

        for (RateLimitersType rateLimiters: RateLimitersType.values()) {
            if (rateLimiters.getCode().equals(code)) {
                retVal = rateLimiters;
            }
        }

        return retVal;
    }

}
