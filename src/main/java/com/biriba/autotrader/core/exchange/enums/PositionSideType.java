package com.biriba.autotrader.core.exchange.enums;

public enum PositionSideType {
    BOTH("Both"),
    LONG("Long"),
    SHORT("Short");
    
	private String description;

    private PositionSideType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
