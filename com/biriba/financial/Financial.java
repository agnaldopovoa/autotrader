package com.biriba.financial;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Financial {
    public static void main(String[] args) {
        System.out.println("\n*****   Library class for exceute financial functions   *****\n");
    }

    public static double ema(String asset, int length, String timeframe) {
        double down = -50.0 * (3.0/length);
        double up = 50.0 * (3.0/length);
        double desv =(Math.random() * (up - down + 1) + down);

        int min = 18100;
        int max = 25200;
        double rand = (Math.random() * (max - min + 1) + min);
        double d_ema = new Double(rand * (1 + (desv/100.0)));
        BigDecimal ema = new BigDecimal(d_ema);
        return ema.setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public static double rsi(String asset, int length, String timeframe) {
        double down = 22;
        double up = 90.0;
        double desv =(Math.random() * (up - down + 1) + down);

        BigDecimal rsi = new BigDecimal(desv);
        return rsi.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void entry(String asset, String direction, double value, double qtde) {
        System.out.println( "Entry " + direction +" in " + asset + " value: " + value + " qtde: " + qtde );
    }

    public static void exit(String asset, char direction, double value, double qtde) {
        System.out.println( "Exit " + direction + " in " + asset + " value: " + value + " qtde: " + qtde );
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void log(double msg) {
        System.out.println(msg);
    }
}
