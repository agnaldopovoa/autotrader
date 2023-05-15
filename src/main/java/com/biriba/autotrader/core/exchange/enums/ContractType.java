package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContractType {
    FUTURE("FUTURE", "Future"),
    CURRENT_MONTH("CURRENT_MONTH", "Current Month"),
    NEXT_MONTH("NEXT_MONTH", "Next Month"),
    CURRENT_QUARTER("CURRENT_QUARTER", "Current Quarter"),
    NEXT_QUARTER("NEXT_QUARTER", "Next Quarter"),
    PERPETUAL_DELIVERING("PERPETUAL_DELIVERING", "Perpetual Delivering");
    
    private String code;
	private String description;

    private ContractType(String code, String description) {
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
    public static final ContractType getContractTypeByCode(String code) {
        ContractType retVal = null;

        for (ContractType contract: ContractType.values()) {
            if (contract.getDescription().equalsIgnoreCase(code)) {
                retVal = contract;
            }
        }

        return retVal;
    }
}
