package com.github.antoniocelso.beerstock.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConvertionUtils {

    public static String asJsonString(Object bookDTO) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(bookDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }

    }
}
