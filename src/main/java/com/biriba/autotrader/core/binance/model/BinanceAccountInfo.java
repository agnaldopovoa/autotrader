package com.biriba.autotrader.core.binance.model;

import java.math.BigDecimal;

import com.biriba.autotrader.core.exchange.model.AccountInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceAccountInfo extends AccountInfo {
    @JsonProperty("totalMarginBalance")
    private BigDecimal marginBalance;
    private BinancePosition[] positions;

    @Override
    public String toString() {
        return super.toString();
    }

}
