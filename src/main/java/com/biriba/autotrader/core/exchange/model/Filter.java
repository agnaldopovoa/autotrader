package com.biriba.autotrader.core.exchange.model;

import java.math.BigDecimal;

import com.biriba.autotrader.core.exchange.enums.FilterType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {
    private FilterType filterType;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal tickSize;
    private BigDecimal minQty;
    private BigDecimal maxQty;
    private BigDecimal stepSize;
    private BigDecimal limit;
    private BigDecimal multiplierUp;
    private BigDecimal multiplierDown;
    private BigDecimal multiplierDecimal;
    private BigDecimal notional;
}
