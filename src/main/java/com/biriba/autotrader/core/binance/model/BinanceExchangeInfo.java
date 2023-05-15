package com.biriba.autotrader.core.binance.model;

import com.biriba.autotrader.core.exchange.model.AssetInfo;
import com.biriba.autotrader.core.exchange.model.ExchangeInfo;
import com.biriba.autotrader.core.exchange.model.Filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BinanceExchangeInfo extends ExchangeInfo {
    private AssetInfo[] assets;
    private Filter[] exchangeFilters;
    private String futuresType;
    private long serverTime;
    private BinanceSymbol[] symbols;
}
