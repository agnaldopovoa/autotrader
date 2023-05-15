package com.biriba.autotrader.core.binance.mapper;

import com.biriba.autotrader.core.binance.model.BinanceAccountInfo;
import com.biriba.autotrader.core.binance.model.BinancePosition;

public class BinanceAccountInfoConverter {
    private BinanceAccountInfoConverter(){}

    public static void convert(BinanceAccountInfo binanceAccountInfo) {
        for (BinancePosition binancePosition : binanceAccountInfo.getPositions()) {
            binancePosition.setQuantity(binancePosition.getPositionAmt());
            binancePosition.setInitialMargin(binancePosition.getPositionInitialMargin());
            binancePosition.setSide(binancePosition.getPositionSide());
        }
    }
}
