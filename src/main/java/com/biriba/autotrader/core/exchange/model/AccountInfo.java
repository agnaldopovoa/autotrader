package com.biriba.autotrader.core.exchange.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountInfo {
    private BigDecimal availableBalance;
    private BigDecimal maxWithdrawAmount;
    private BigDecimal walletBalance;
    private BigDecimal marginBalance;
    private BigDecimal totalInitialMargin;
    private BigDecimal totalMaintMargin;
    private BigDecimal totalUnrealizedProfit;
    private boolean    canWithdraw;
    private Position[] positions;

    @Override
    public String toString() {
        String strPositions = toStringPositions();

        return "availableBalance: " + this.getAvailableBalance() + "\n" +
               (this.canWithdraw ? "maxWithdrawAmount: " + this.getMaxWithdrawAmount() + "\n": "") + 
               "marginBalance: " + this.getMarginBalance() + "\n" +
               (!strPositions.isEmpty() ? strPositions + 
                        "totalInitialMargin: " + this.getTotalInitialMargin() + "\n" +
                        "totalMaintMargin: " + this.getTotalMaintMargin() + "\n" +
                        "totalUnrealizedProfit: " + this.getTotalUnrealizedProfit() : "");
    }

    private String toStringPositions() {
        StringBuilder result = new StringBuilder();
        
        for (Position position : this.getPositions()) {
            if (position.getQuantity().floatValue() != 0.0) {
                if (result.toString().isEmpty())
                    result.append("Positions: \n");

                result.append("\tsymbol: " + position.getSymbol() + "\n");
                result.append("\tentryPrice: " + position.getEntryPrice() + "\n");
                result.append("\tquantity: " + position.getQuantity() + "\n");
                result.append("\tleverage: " + position.getLeverage() + "\n");
                result.append("\tisolated: " + position.isIsolated() + "\n");
                result.append("\tside: " + position.getSide() + "\n");
                result.append("\tinitialMargin: " + position.getInitialMargin() + "\n");
                result.append("\tunrealizedProfit: " + position.getUnrealizedProfit() + "\n");
            }
        }

        return result.toString();
    }
}
