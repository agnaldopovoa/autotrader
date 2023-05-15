package com.biriba.autotrader.core.exchange.enums;

//import com.fasterxml.jackson.annotation.JsonCreator;

public enum ResponseType {
    ACK ("ACK"),
    RESULT("Result");
    
	private String description;

    private ResponseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
