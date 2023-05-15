package com.biriba.autotrader.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Financial {
    static final Logger logger = Logger.getLogger(Financial.class.getName());

    public static Date mainEx() {
        logger.log(Level.INFO, "\n*****   Class for execute financial functions   *****\n");
        return Calendar.getInstance().getTime();
    }

    public double ema(String asset, int length, String timeframe) {
        double down = -50.0 * (3.0/(length + asset.length() + timeframe.length()));
        double up = 50.0 * (3.0/length);
        double desv =(Math.random() * (up - down + 1) + down);

        int min = 18100;
        int max = 25200;
        double rand = (Math.random() * (max - min + 1) + min);
        double dEma = rand * (1 + (desv/100.0));
        BigDecimal ema = BigDecimal.valueOf(dEma);
        return ema.setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public double rsi(String asset, int length, String timeframe) {
        double down = 22;
        double up = 90.0;
        double desv =(Math.random() * (up - down + 1) + down);

        BigDecimal rsi = BigDecimal.valueOf(desv + (length + asset.length() + timeframe.length()));
        return rsi.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void entry(String asset, String direction, double value, double qtde) {
        logger.log(Level.INFO, "Entry {0} in {1} value: {2} qtde: {3}", new Object[]{direction, asset, value, qtde});
    }

    public void exit(String asset, char direction, double value, double qtde) {
        logger.log(Level.INFO, "Exit {0} in {1} value: {2} qtde: {3}", new Object[]{direction, asset, value, qtde});
    }

    public void log(String msg) {
        logger.log(Level.INFO, "{0}", msg);
    }
}
