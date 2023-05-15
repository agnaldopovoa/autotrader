package com.biriba.autotrader.core.binance.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.biriba.autotrader.core.exchange.enums.ContractStatusType;
import com.biriba.autotrader.core.exchange.model.Filter;
import com.biriba.autotrader.core.exchange.model.SymbolInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BinanceSymbol extends SymbolInfo{
    private BigDecimal requiredMarginPercent;
    private ContractStatusType status;
    private int quantityPrecision;
    private BigDecimal triggerProtect;
    private Filter[] filters;
    private String underlyingType;
    private String[] underlyingSubType;
    private int maxMoveOrderLimit;

    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Timestamp deliveryDate;
    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Timestamp onboardDate;
    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Timestamp settlePlan;
}
