package com.biriba.autotrader.core.utils;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class UnixTimestampDeserializer extends JsonDeserializer<Date> {
    static Logger logger = Logger.getLogger(UnixTimestampDeserializer.class.getName());

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String timestamp = jp.getText().trim();

        try {
            return new Date(Long.valueOf(timestamp + "000"));
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Unable to deserialize timestamp: {0}", timestamp);
            return null;
        }
    }
}