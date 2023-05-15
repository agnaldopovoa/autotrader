package com.biriba.autotrader.core.exchange.model;

import lombok.Data;

@Data
public class ExchangeInfo {
    private RateLimits[] rateLimits;
    private SymbolInfo[] symbols;
    private String timezone;
}
