package com.biriba.autotrader.core.exchange;

import com.biriba.autotrader.core.exchange.enums.SecurityType;

import lombok.Data;

@Data
public class ApiService {
    private String apiEndpoint;
    private SecurityType securityType; 
}
