package com.chukwuma.sdet.tests.api.config;

public class ApiConfig {

        public static final String BASE_URL = System.getProperty("api.base.url",
                        System.getenv().getOrDefault(
                                        "API_BASE_URL",
                                        "https://jsonplaceholder.typicode.com"));

        public static final String AUTH_BASE_URL = System.getProperty("api.auth.base.url",
                        System.getenv().getOrDefault(
                                        "API_AUTH_BASE_URL",
                                        "https://dummyjson.com/"));
}