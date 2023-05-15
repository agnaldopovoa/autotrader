package com.biriba.autotrader.core.exchange.model;

import java.math.BigDecimal;
import java.util.Date;

import com.biriba.autotrader.core.exchange.enums.PositionSideType;
import com.biriba.autotrader.core.utils.UnixTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
public class Position {
    private String symbol;
    private BigDecimal entryPrice;
    private BigDecimal quantity;
    private int leverage;
    private boolean isolated;
    private BigDecimal initialMargin;
    private PositionSideType side;
    private BigDecimal unrealizedProfit;
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Date updateTime;
}
