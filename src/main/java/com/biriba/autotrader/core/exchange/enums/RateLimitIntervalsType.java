package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RateLimitIntervalsType {
    MINUTE("MINUTE","Minute"),
    SECOND("SECOND","Second");

    private String code;
	private String description;

    private RateLimitIntervalsType(String code, String description) {
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
    public static final RateLimitIntervalsType getRateLimitIntervalsTypeByCode(String code) {
        RateLimitIntervalsType retVal = null;

        for (RateLimitIntervalsType rateLimitIntervals: RateLimitIntervalsType.values()) {
            if (rateLimitIntervals.getCode().equals(code)) {
                retVal = rateLimitIntervals;
            }
        }

        return retVal;
    }
    
}
