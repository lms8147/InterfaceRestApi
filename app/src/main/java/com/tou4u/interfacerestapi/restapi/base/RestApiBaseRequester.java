package com.tou4u.interfacerestapi.restapi.base;

import com.google.gson.Gson;
import com.tou4u.interfacerestapi.httpconnection.cast.HttpStringRequester;

import java.util.HashMap;
import java.util.Map;

public abstract class RestApiBaseRequester<GetReqVO, PostReqVO, UpdateReqVO, DeleteReqVO> implements RestApiRequester<GetReqVO, PostReqVO, UpdateReqVO, DeleteReqVO> {

    private final HttpStringRequester mHttpStringRequester;
    private final Map<String, String> mHttpHeader;
    public final String mBaseURL;

    public RestApiBaseRequester(String url) {
        mHttpStringRequester = new HttpStringRequester();
        mHttpHeader = new HashMap<>();
        mHttpHeader.put("Cache-Control", "no-cache");
        mHttpHeader.put("Content-Type", "application/json");
        mHttpHeader.put("Accept", "application/json");
        mBaseURL = url;

    }

    public <ReqVO, ResVO> ResVO requestVOWithGSON(String specificURL, Map<String, Object> params, Class<ResVO> responseType) {
        String json = mHttpStringRequester.requestGetData(specificURL, mHttpHeader, params);
        Gson gson = new Gson();
        return gson.fromJson(json, responseType);
    }
}
