package com.tou4u.interfacerestapi.httpconnection.cast;

import com.tou4u.interfacerestapi.httpconnection.HttpRequester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpStringRequester extends HttpRequester<String> {

    @Override
    public String getData(HttpURLConnection httpConnection, String param) {
        try {

            int responseStatusCode = httpConnection.getResponseCode();
            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
            } else {
                inputStream = httpConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}