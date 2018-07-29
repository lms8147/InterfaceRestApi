package com.tou4u.interfacerestapi.restapi.base;

import com.google.gson.Gson;
import com.tou4u.interfacerestapi.httpconnection.cast.HttpStringRequester;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class RestApiBaseRequester implements RestApiRequester {

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

    public <ReqVO, ResVO> ResVO excuteMethod(@Method int method, ReqVO requestVO, Class<ResVO> responseType) {

        Class reqeustType = requestVO.getClass();

        RequestVO voType = (RequestVO) reqeustType.getAnnotation(RequestVO.class);
        if(voType == null){
            return null;
        }else{
            if(voType.methodType() != method){
                return null;
            }
        }

        Map<String, Object> params = new HashMap<>();
        String specificURL = mBaseURL;
        try {
            for (Field field : reqeustType.getFields()) {
                Argument typeArgument = field.getAnnotation(Argument.class);
                if (typeArgument != null) {
                    String name = field.getName();
                    Object value = null;

                    value = field.get(requestVO);

                    specificURL = specificURL.replace(name, value.toString());
                } else {
                    Parameter typeParameter = field.getAnnotation(Parameter.class);
                    if (typeParameter != null) {
                        String name = field.getName();
                        Object value = field.get(requestVO);
                        params.put(name, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (params.size() == 0)
            params = null;

        String json = null;
        switch (method) {
            case METHOD_DELETE:
                json = mHttpStringRequester.requestDeleteData(specificURL, mHttpHeader, null, params);
                break;
            case METHOD_GET:
                json = mHttpStringRequester.requestGetData(specificURL, mHttpHeader, params);
                break;
            case METHOD_POST:
                json = mHttpStringRequester.requestPostData(specificURL, mHttpHeader, null, params);
                break;
            case METHOD_UPDATE:
                json = mHttpStringRequester.requestUpdateData(specificURL, mHttpHeader, null, params);
                break;
        }
        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, responseType);
        } else {
            return null;
        }

    }

    @Documented
    @Target(ElementType.FIELD)
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Argument {
    }

    @Documented
    @Target(ElementType.FIELD)
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Parameter {
    }
}
