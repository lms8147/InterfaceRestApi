package com.tou4u.interfacerestapi.httpconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnectionFactory {

    private void setHTTPS(HttpURLConnection httpURLConnection){
        try{
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            /**
             * set secure thing
             * current : default setting
             */
        }catch (ClassCastException e) {
            /**
             * if url is not https catch the exception
             */
            e.printStackTrace();
        }
    }

    public HttpURLConnection httpConnect(boolean useHTTPS, String urlString, boolean read, boolean write, String method,
                                         Map<String, String> header) {
        URL url;
        try {
            url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if(useHTTPS){
                setHTTPS(httpURLConnection);
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoInput(read);
            httpURLConnection.setDoOutput(write);

            if (header != null) {
                Iterator<Entry<String, String>> i = header.entrySet().iterator();
                while (true) {
                    Entry<String, String> h = i.next();
                    httpURLConnection.setRequestProperty(h.getKey(), h.getValue());
                    if (!i.hasNext()) {
                        break;
                    }
                }
            }

            httpURLConnection.connect();
            return httpURLConnection;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}