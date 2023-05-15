package com.biriba.autotrader.core.exchange.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AssetInfo {
    private String asset;
    private BigDecimal autoAssetExchange;
    private boolean marginAvailable;
}
