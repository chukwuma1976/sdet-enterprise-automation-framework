package com.chukwuma.sdet.utils;

import com.chukwuma.sdet.models.LoginData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class TestDataLoader {

    public static LoginData loadLoginData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    new File("src/test/resources/testdata/loginData.json"),
                    LoginData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load login data", e);
        }
    }
}