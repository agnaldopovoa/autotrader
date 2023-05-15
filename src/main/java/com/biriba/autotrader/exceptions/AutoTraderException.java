package com.biriba.autotrader.exceptions;

public class AutoTraderException extends RuntimeException {
    // ***************                 ******************    
    private static final long serialVersionUID = 4360108982268949009L;

    public static final String RUNTIME_ERROR = "RuntimeError";
    public static final String SIGNATURE_ERROR = "SignatureError";
    public static final String URI_PARSE_ERROR = "UriParseError";
    public static final String JSON_PARSE_ERROR = "JsonParseError";
    public static final String SYS_ERROR = "SysError";

    public static final String INPUT_ERROR = "InputError";
    public static final String KEY_MISSING = "KeyMissing";
    public static final String SUBSCRIPTION_ERROR = "SubscriptionError";
    public static final String ENV_ERROR = "EnvironmentError";
    public static final String EXEC_ERROR = "ExecuteError";
    private final String errCode;

    public AutoTraderException(String errType, String errMsg) {
        super(errMsg);
        this.errCode = errType;
    }

    public AutoTraderException(String errType, String errMsg, Throwable e) {
        super(errMsg, e);
        this.errCode = errType;
    }

    public String getErrType() {
        return this.errCode;
    }}
