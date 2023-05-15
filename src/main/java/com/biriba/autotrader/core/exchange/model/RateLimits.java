package com.biriba.autotrader.core.exchange.model;

import com.biriba.autotrader.core.exchange.enums.RateLimitIntervalsType;
import com.biriba.autotrader.core.exchange.enums.RateLimitersType;

import lombok.Data;

@Data
public class RateLimits {
    RateLimitIntervalsType interval;
    int intervalNum;
    int limit;
    RateLimitersType rateLimitType;
}
