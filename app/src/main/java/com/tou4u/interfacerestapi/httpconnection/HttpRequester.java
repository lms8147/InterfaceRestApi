package com.tou4u.interfacerestapi.httpconnection;

import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class HttpRequester<T> {

    private HttpConnectionFactory httpConnectionFactory;

    public HttpRequester() {
        httpConnectionFactory = new HttpConnectionFactory();
    }

    private String buildURL(String url, Map<String, Object> args){
        String arg = null;
        if (args != null) {
            arg = "";
            Iterator<Entry<String, Object>> i = args.entrySet().iterator();
            while (true) {
                Entry<String, Object> e = i.next();
                String a = String.format("%s=%s", e.getKey(), e.getValue());
                arg += a;
                if (i.hasNext()) {
                    arg += "&";
                } else {
                    break;
                }
            }
        }

        if (arg != null) {
            url += ("?" + arg);
        }
        return url;
    }

    private String buildBodyParams(Map<String, Object> params){
        String param = null;
        if (params != null) {
            param = "";
            Iterator<Entry<String, Object>> i = params.entrySet().iterator();
            while (true) {
                Entry<String, Object> e = i.next();
                String p = String.format("%s=%s", e.getKey(), e.getValue());
                param += p;
                if (i.hasNext()) {
                    param += "&";
                } else {
                    break;
                }
            }
        }
        return param;
    }

    public abstract T getData(HttpURLConnection httpConnection, String param);

    public T requestGetData(String url, Map<String, String> header, Map<String, Object> params) {
        T result = receiveGetData(url, header, params);
        return result;
    }

    private T receiveGetData(String url, Map<String, String> header, Map<String, Object> params) {

        if (params != null) {
            url = buildURL(url, params);
        }

        HttpURLConnection httpConnection = httpConnectionFactory.httpConnect(false,url, true, false, "GET", header);

        T result = getData(httpConnection, null);
        return result;
    }

    public T requestPostData(String url, Map<String, String> header, Map<String, Object> args,
                             Map<String, Object> params) {
        T result = receivePostData(url, header, args, params);
        return result;
    }

    private T receivePostData(String url, Map<String, String> header, Map<String, Object> args,
                              Map<String, Object> params) {

        if (args != null) {
            url = buildURL(url, args);
        }

        boolean write = (params != null);
        HttpURLConnection httpConnection = httpConnectionFactory.httpConnect(false, url, true, write, "POST", header);

        String param = buildBodyParams(params);

        T result = getData(httpConnection, param);
        return result;
    }

    public T requestUpdateData(String url, Map<String, String> header, Map<String, Object> args,
                             Map<String, Object> params) {
        T result = receivePostData(url, header, args, params);
        return result;
    }

    private T receiveUpdateData(String url, Map<String, String> header, Map<String, Object> args,
                              Map<String, Object> params) {

        if (args != null) {
            url = buildURL(url, args);
        }

        boolean write = (params != null);
        HttpURLConnection httpConnection = httpConnectionFactory.httpConnect(false,url, true, write, "UPDATE", header);

        String param = buildBodyParams(params);

        T result = getData(httpConnection, param);
        return result;
    }

    public T requestDeleteData(String url, Map<String, String> header, Map<String, Object> args,
                             Map<String, Object> params) {
        T result = receiveDeleteData(url, header, args, params);
        return result;
    }

    private T receiveDeleteData(String url, Map<String, String> header, Map<String, Object> args,
                              Map<String, Object> params) {

        if (args != null) {
            url = buildURL(url, args);
        }

        boolean write = (params != null);
        HttpURLConnection httpConnection = httpConnectionFactory.httpConnect(false,url, true, write, "DELETE", header);

        String param = buildBodyParams(params);

        T result = getData(httpConnection, param);
        return result;
    }

}
