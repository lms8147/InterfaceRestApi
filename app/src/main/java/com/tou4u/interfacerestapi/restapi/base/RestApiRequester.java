package com.tou4u.interfacerestapi.restapi.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * one resource - one object
 * one resource - at most 4 method(GET, POST, UPDATE, DELETE)
 */
public interface RestApiRequester {

    @Retention(SOURCE)
    @Inherited
    @IntDef({METHOD_GET, METHOD_POST, METHOD_UPDATE, METHOD_DELETE})
    public @interface Method {
    }

    int METHOD_GET = 0;
    int METHOD_POST = 1;
    int METHOD_UPDATE = 2;
    int METHOD_DELETE = 3;

    public <ReqVO, ResVO> ResVO excuteMethod(@Method int method, ReqVO requestVO, Class<ResVO> responseType);

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface RequestVO {
        @Method int methodType();
    }

}
