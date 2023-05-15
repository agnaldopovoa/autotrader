package com.biriba.autotrader.core.binance.model;

import java.math.BigDecimal;

import com.biriba.autotrader.core.exchange.enums.PositionSideType;
import com.biriba.autotrader.core.exchange.model.Position;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper=false)
public class BinancePosition extends Position{
    private BigDecimal positionAmt;
    private BigDecimal positionInitialMargin;
    private PositionSideType positionSide;
}