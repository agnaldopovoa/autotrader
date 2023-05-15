package com.biriba.autotrader.core.exchange.enums;

public enum SecretType {
    SECRET_KEY("Api secret"),
    PRIVATE_FILE("Private file");

	private String description;

    private SecretType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
