package com.biriba.autotrader;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.biriba.autotrader.core.binance.BinanceFutureAdapter;
import com.biriba.autotrader.core.binance.BinanceSpotAdapter;
import com.biriba.autotrader.core.exchange.Exchange;
import com.biriba.autotrader.core.exchange.enums.NetworkType;
import com.biriba.autotrader.core.exchange.model.AccountInfo;
import com.biriba.autotrader.core.exchange.model.ExchangeInfo;
import com.biriba.autotrader.strategy.Financial;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.MapContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoTrader {	
    static Logger logger = Logger.getLogger(AutoTrader.class.getName());

    public static void main(String[] args) {
        if (Calendar.getInstance().getTime().getTime() == (new Date().getTime()-200)) {
            logger.log(Level.INFO, "\nEvaluating expression...");
            testExpression("com.biriba.autotrader.strategy.Financial.mainEx()", Financial.class);

            logger.log(Level.INFO, "\nRuning script...");
            execScript();
        }

        try {
            Exchange binanceSpot = new BinanceSpotAdapter(NetworkType.MAINNET);
            ExchangeInfo exchangeInfo = binanceSpot.getExchangeInfo();
            if (exchangeInfo != null) {
                logger.log(Level.WARNING, "\n{0}" ,exchangeInfo);
            }

            // Exchange binanceFuture = new BinanceFutureAdapter(NetworkType.TESTNET);
            // AccountInfo account = binanceFuture.getAccountInfo();
            // if (account != null) {
            //     logger.log(Level.INFO, "\n{0}", account);
            // }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (Calendar.getInstance().getTime().getTime() == (new Date().getTime()-200)) {
            SpringApplication.run(AutoTrader.class, args);
        }
    }

    private static void testExpression(String expresion, Class<?> c) {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();
        
        // Create an expression
        JexlExpression e = jexl.createExpression(expresion);
        
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set(c.getName(), c );
        
        // Now evaluate the expression, getting the result
        Date today = (Date)e.evaluate(jc); 
        logger.log(Level.INFO, "Today is {0}\n", today);
    }

    private static void execScript() {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();

        // Create an expression
        StringBuilder sb = new StringBuilder();
        sb.append("var ema_8 = financial.ema(\"BTC\", 8, \"1D\");");
        sb.append("var ema_50 = financial.ema(\"BTC\", 50, \"1D\");");
        sb.append("var rsi_8 = financial.rsi(\"BTC\", 50, \"1D\");");
        sb.append("financial.log(\"(\" + ema_8 +\" > \" + ema_50 +\") && (\" + rsi_8 +\" > 57.0)\")");
        sb.append("if ((ema_8 > ema_50) && (rsi_8 > 57.0)) {");
        sb.append("   financial.entry(\"BTC\", \"b\", ema_8, 0.01);");
        sb.append("}");

        // JexlExpression e = jexl.createExpression( sb.toString() );
        JexlScript jscr = jexl.createScript( sb.toString() );
        
        // Create a context and add data
        JexlContext jc = new MapContext();
        Financial financial = new Financial();
        jc.set("financial",  financial);
        
        // Now execute the script
        jscr.execute(jc);
        logger.log(Level.INFO, "\n\n------------------------------\n");
    }
}

