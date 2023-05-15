package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContractStatusType {
    PENDING_TRADING("PENDING_TRADING", "Pending Trading"),
    TRADING("TRADING", "Trading"),
    PRE_DELIVERING("PRE_DELIVERING", "Pre-Delivering"),
    DELIVERING("DELIVERING", "Delivering"),
    DELIVERED("DELIVERED", "Delivered"),
    PRE_SETTLE("PRE_SETTLE" ,"Pre-Settle"),
    SETTLING("SETTLING", "Settling"),
    CLOSE("CLOSE", "Close");
    
	private String code;
	private String description;

    private ContractStatusType(String code, String description) {
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
    public static final ContractStatusType getContractStatusTypeByCode(String code) {
        ContractStatusType retVal = null;

        for (ContractStatusType contractStatus: ContractStatusType.values()) {
            if (contractStatus.getDescription().equalsIgnoreCase(code)) {
                retVal = contractStatus;
            }
        }

        return retVal;
    }
}
