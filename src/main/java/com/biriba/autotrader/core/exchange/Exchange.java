package com.biriba.autotrader.core.exchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.biriba.autotrader.core.exchange.model.AccountInfo;
import com.biriba.autotrader.core.exchange.model.ExchangeInfo;

public abstract class Exchange {
    // Base exchange URL
    protected String baseURL = "";
    // Request timeout
    protected int timeout = 5000;

    protected List<ApiService> services = new ArrayList<>();
 
    public abstract ExchangeInfo getExchangeInfo() throws IOException, InterruptedException;

    public abstract AccountInfo getAccountInfo() throws IOException, InterruptedException ;
}
