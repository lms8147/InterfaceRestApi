package com.tou4u.interfacerestapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tou4u.interfacerestapi.restapi.UserApi;
import com.tou4u.interfacerestapi.restapi.base.RestApiRequester;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                UserApi userApi = new UserApi();
                userApi.excuteMethod(RestApiRequester.METHOD_GET, new UserApi.GetReqVO(2),UserApi.GetResVO.class);
                return null;
            }
        }.execute();

    }
}
