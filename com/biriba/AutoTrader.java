package com.biriba;

import com.biriba.financial.Financial;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;

public class AutoTrader {	
    public static void main(String[] args) {
        System.out.println("\nEvaluating expression...");
        testExpression("Financial.main()", Financial.class);

        System.out.println("\nRuning script...");
        execScript();
    }

    private static double testExpression(String expresion, Class<?> c) {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();
        
        // Create an expression
        JexlExpression e = jexl.createExpression(expresion);
        
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set(c.getName(), c );
        
        // Now evaluate the expression, getting the result
        Object o = e.evaluate(jc); 
        if (o == null) {
            o = new Double(0);
        }

        return (double)o;
    }

    private static void execScript() {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();
        
        // Create an expression
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("var ema_8 = Financial.ema(\"BTC\", 8, \"1D\");");
        sb.append("var ema_50 = Financial.ema(\"BTC\", 50, \"1D\");");
        sb.append("var rsi_8 = Financial.rsi(\"BTC\", 50, \"1D\");");
        sb.append("Financial.log(\"ema_8: \" + ema_8);");
        sb.append("Financial.log(\"ema_50: \" + ema_50);");
        sb.append("Financial.log(\"rsi_8: \" + rsi_8);");
        sb.append("if ((ema_8 > ema_50) && (rsi_8 > 57.0)) {");
        sb.append("   Financial.entry(\"BTC\", \"b\", ema_8, 0.01);");
        sb.append("}");

        // JexlExpression e = jexl.createExpression( sb.toString() );
        JexlScript jscr = jexl.createScript( sb.toString() );
        
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("Financial", Financial.class );
        
        // Now execute the script
        jscr.execute(jc);
    }
}

