package com.biriba.autotrader.core.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpRequest.Builder;
import java.nio.file.Paths;
import java.util.Date;

import com.biriba.autotrader.core.utils.http.enums.HttpExVerb;
import com.biriba.autotrader.exceptions.AutoTraderException;


public class HttpExRequest {
    private HttpExRequest(){}

    public static HttpResponse<String> callAPI(String baseURL, String serviceURL, HttpExVerb verb, String params) 
        throws IOException, InterruptedException {
        URI targetURI;
        try {
            targetURI = new URI(baseURL + serviceURL + (params != null && !params.isEmpty() ? "?" + params : ""));
        } catch (URISyntaxException e) {
            throw new AutoTraderException(AutoTraderException.URI_PARSE_ERROR, ".Error parsing service URL.\n" + e.getLocalizedMessage());
        }

        Builder httpRequestBuilder = HttpRequest.newBuilder().uri(targetURI);
        
        switch(verb) {
            case POST:
                httpRequestBuilder = httpRequestBuilder.POST(null);
                break;
            case PUT:
                httpRequestBuilder = httpRequestBuilder.PUT(null);
                break;
            case DELETE:
                httpRequestBuilder = httpRequestBuilder.DELETE();
                break;
            default:
                httpRequestBuilder = httpRequestBuilder.GET();
        }
                
        HttpRequest httpRequest = httpRequestBuilder.build();
        
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }  

    public static HttpResponse<String> callSecurityAPI(String baseURL, String serviceURL, HttpExVerb verb, String params,
        String apiKey, String privateKey, String privateKeyPath)
        throws IOException, InterruptedException {
        
        Date now = new Date();
        String paramTimestamp = "timestamp=" + Long.toString(now.getTime());
        String paramsWithTimestamp = (params != null && !params.isEmpty()? params + "&" : "") + paramTimestamp;

        String signature;
        if (privateKey != null && !privateKey.isBlank())
            signature = HttpExSignature.signSHA256RSA(paramsWithTimestamp, privateKey);
        else if (privateKeyPath != null && !privateKeyPath.isBlank())
            signature = HttpExSignature.signSHA256RSA(paramsWithTimestamp, Paths.get(privateKeyPath));
        else
            signature = "";


        URI targetURI;
        try {
            signature = URLEncoder.encode(signature, StandardCharsets.UTF_8.toString());
            targetURI = new URI(baseURL + serviceURL + "?" + paramsWithTimestamp + "&signature=" + signature);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new AutoTraderException(AutoTraderException.URI_PARSE_ERROR, ".Error parsing service URL.\n" + e.getLocalizedMessage());
        } 
        Builder httpRequestBuilder = HttpRequest.newBuilder().uri(targetURI);
        httpRequestBuilder = httpRequestBuilder.setHeader("X-MBX-APIKEY", apiKey);

        switch(verb) {
            case POST:
                httpRequestBuilder = httpRequestBuilder.POST(null);
                break;
            case PUT:
                httpRequestBuilder = httpRequestBuilder.PUT(null);
                break;
            case DELETE:
                httpRequestBuilder = httpRequestBuilder.DELETE();
                break;
            default:
                httpRequestBuilder = httpRequestBuilder.GET();
        }
                
        HttpRequest httpRequest = httpRequestBuilder.build();
        
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}
