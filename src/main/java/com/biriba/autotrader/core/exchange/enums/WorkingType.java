package com.biriba.autotrader.core.exchange.enums;

// import com.fasterxml.jackson.annotation.JsonCreator;

public enum WorkingType {
    MARK_PRICE ("Mark Price"),
    CONTRACT_PRICE("Contract Price");
    
	private String description;

    private WorkingType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
