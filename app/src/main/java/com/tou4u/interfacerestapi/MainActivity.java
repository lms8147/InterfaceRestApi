package com.tou4u.interfacerestapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tou4u.interfacerestapi.restapi.UserApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                UserApi userApi = new UserApi();
                userApi.excuteDelete(new UserApi.DeleteReqVO(1),null);
                return null;
            }
        }.execute();

    }
}
