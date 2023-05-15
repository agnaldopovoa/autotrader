package com.biriba.autotrader.core.utils.http.enums;

public enum HttpExResponse {
    HTTP_OK(200,"Ok"),
    HTTP_BAD_REQUEST(400,"Bad request"),
    HTTP_ACCESS_DENIED(403,"Access denied"),
    HTTP_RESOURCE_NOT_FOUND(404,"Resource not found"),
    HTTP_BREAK_RATE_LIMIT(429,"Break rate limite"),
    HTTP_IP_BANNED(418,"Ip banned"),
    HTTP_SERVER_ERROR(500,"Internal server error");

    private int code;
    private String description;

    private HttpExResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static String getDescriptionByCode(int code) {
        HttpExResponse retVal = null;

        for (HttpExResponse httpExResponse: HttpExResponse.values()) {
            if (httpExResponse.getCode() == code) {
                retVal = httpExResponse;
            }
        }

        if (retVal != null)
            return retVal.getDescription();
        else
            return "Http code not found";
     }
}
