package com.biriba.autotrader.core.binance;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.biriba.autotrader.core.binance.mapper.BinanceAccountInfoConverter;
import com.biriba.autotrader.core.binance.model.BinanceAccountInfo;
import com.biriba.autotrader.core.binance.model.BinanceExchangeInfo;
import com.biriba.autotrader.core.exchange.Exchange;
import com.biriba.autotrader.core.exchange.enums.NetworkType;
import com.biriba.autotrader.core.exchange.model.AccountInfo;
import com.biriba.autotrader.core.exchange.model.ExchangeInfo;
import com.biriba.autotrader.core.utils.http.HttpExRequest;
import com.biriba.autotrader.core.utils.http.enums.HttpExResponse;
import com.biriba.autotrader.core.utils.http.enums.HttpExVerb;
import com.biriba.autotrader.exceptions.AutoTraderException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinanceFutureAdapter extends Exchange{
    static Logger logger = Logger.getLogger(BinanceFutureAdapter.class.getName());
    
    // ***********************     M A I N N E T     **************************
    private static final String MAINNET_URL          = "https://fapi.binance.com";
    private static final String MAINNET_API_KEY      = "gMnN87w...SZ08fc0";
    private static final String MAINNET_PRIVATE_FILE = "/home/apovoa/rsa-key/private.pem";
    // ***********************     T E S T N E T     **************************
    private static final String TESTNET_URL        = "https://testnet.binancefuture.com";
    private static final String TESTNET_API_KEY    = "30030f7798324a83696165e761e1e398e47adceafee53b9ae19a938d699ea4b5";
    private static final String TESTNET_API_SECRET = "6ca645ca8cdb442612445dbd71b2e0772def862681252c16e4c9beec21a96f9a";

    // ***********************     S E R V I C E S     **************************
    private static final String EXCHANGE_INFO = "/fapi/v1/exchangeInfo";
    private static final String ACCOUNT_INFO  = "/fapi/v2/account";

    private static final int TIME_OUT = 12000;

    private NetworkType network;
    private String apiKey;
    private String secretKey;
    private String privateFile;

    public BinanceFutureAdapter() {
        this.network = NetworkType.TESTNET;
        setOptions();
    }

    public BinanceFutureAdapter(NetworkType network) {
        this.network = network;
        setOptions();
    }

    private void setOptions() {
        timeout = TIME_OUT;

        if (NetworkType.MAINNET == network) {
            baseURL = MAINNET_URL;
            apiKey = MAINNET_API_KEY;
            secretKey = null;
            privateFile = MAINNET_PRIVATE_FILE;
        } else {
            baseURL = TESTNET_URL;
            apiKey = TESTNET_API_KEY;
            secretKey = TESTNET_API_SECRET;
            privateFile = null;
        }
    }
    
    public ExchangeInfo getExchangeInfo() 
        throws IOException, InterruptedException {
        ExchangeInfo resp = null;

        HttpResponse<String> response = HttpExRequest.callAPI(baseURL, EXCHANGE_INFO, HttpExVerb.GET, null);
        
        if (response.statusCode() == HttpExResponse.HTTP_OK.getCode()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                resp = objectMapper.readValue(response.body(), BinanceExchangeInfo.class);
            } catch (JsonProcessingException e) {
                throw new AutoTraderException(AutoTraderException.JSON_PARSE_ERROR, e.getLocalizedMessage());
            }
        } else {
            logger.log(Level.INFO, "\n  >>>>  BinanceAdapter.getExchangeInfo response.statusCode {0} {1}",
            new Object[]{response.statusCode(), HttpExResponse.getDescriptionByCode(response.statusCode())});
        }

        return resp;
    }


    public AccountInfo getAccountInfo()
        throws IOException, InterruptedException {
        String params = "recvWindow=" + Integer.toString(timeout);

        HttpResponse<String> response = HttpExRequest.callSecurityAPI(baseURL, ACCOUNT_INFO, HttpExVerb.GET, params,
        apiKey, secretKey, privateFile);

        BinanceAccountInfo binanceAccountInfo = null;
        if (response.statusCode() == HttpExResponse.HTTP_OK.getCode()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                binanceAccountInfo = objectMapper.readValue(response.body(), BinanceAccountInfo.class);
            } catch (JsonProcessingException e) {
                throw new AutoTraderException(AutoTraderException.JSON_PARSE_ERROR, e.getLocalizedMessage());
            }
            BinanceAccountInfoConverter.convert(binanceAccountInfo);
        } else {
            logger.log(Level.INFO, "\n  >>>>  BinanceAdapter.getAccountInfo response.statusCode {0} {1}",
            new Object[]{response.statusCode(), HttpExResponse.getDescriptionByCode(response.statusCode())});
        }

        return binanceAccountInfo;
    }
}
