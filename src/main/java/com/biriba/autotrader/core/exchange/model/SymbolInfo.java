package com.biriba.autotrader.core.exchange.model;

import java.math.BigDecimal;

import com.biriba.autotrader.core.exchange.enums.ContractType;
import com.biriba.autotrader.core.exchange.enums.OrderType;
import com.biriba.autotrader.core.exchange.enums.TimeInForceType;

import lombok.Data;

@Data
public class SymbolInfo {
    private String symbol;
    private String baseAsset;
    private int baseAssetPrecision;
    private String marginAsset;
    private String quoteAsset;
    private int quotePrecision;
    private ContractType contractType;
    private BigDecimal liquidationFee;
    private BigDecimal maintMarginPercent;
    private BigDecimal marketTakeBound;
    private OrderType[] orderTypes;
    private TimeInForceType[] timeInForce;
    private String pair;
    private int pricePrecision;
}
