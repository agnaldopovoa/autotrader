package com.biriba.autotrader.core.exchange.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CandlestickIntervalsType {   
    MI_1("1m","1 Minute"),
    MI_3("3m","3 Minutes"),
    MI_5("5m","5 Minutes"),
    MI_15("15m","15 Minutes"),
    MI_30("30m","30 Minutes"),
    HR_1("1h","1 Hour"),
    HR_2("2h","2 Hours"),
    HR_4("4h","4 Hours"),
    HR_6("6h","6 Hours"),
    HR_8("8h","8 Hours"),
    HR_12("12h","12 Hours"),
    DY_1("1d","1 Day"),
    DY_3("3d","3 Days"),
    WK_1("1w","1 Week"),
    MO_1("1M","1 Month");

    private String code;
	private String description;

    private CandlestickIntervalsType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    @JsonCreator
    public static final CandlestickIntervalsType getCandlestickIntervalsTypeByCode(String code) {
        CandlestickIntervalsType retVal = null;

        for (CandlestickIntervalsType candlestickIntervals: CandlestickIntervalsType.values()) {
            if (candlestickIntervals.getCode().equals(code)) {
                retVal = candlestickIntervals;
            }
        }

        return retVal;
    }
}
