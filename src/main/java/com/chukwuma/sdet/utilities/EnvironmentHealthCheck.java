package com.chukwuma.sdet.utilities;

import java.net.HttpURLConnection;
import java.net.URL;

public class EnvironmentHealthCheck {

    private static Boolean environmentAvailable = null;

    public static boolean isEnvironmentAvailable(String urlString) {

        if (environmentAvailable != null) {
            return environmentAvailable;
        }

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();

            environmentAvailable = (responseCode >= 200 && responseCode < 500);

        } catch (Exception e) {
            environmentAvailable = false;
        }

        return environmentAvailable;
    }
}
