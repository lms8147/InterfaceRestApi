package com.tou4u.interfacerestapi.restapi;

import android.util.Log;

import com.tou4u.interfacerestapi.restapi.base.RestApiRequester;
import com.tou4u.interfacerestapi.retrofit.RetrofitRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class UserApi implements RestApiRequester {

    private static String TAG = UserApi.class.getSimpleName();

    private RetrofitRequester mRetrofitRequester;

    public UserApi() {
        mRetrofitRequester = new RetrofitRequester("http://192.168.0.2:8080/api/users/");
    }

    @Override
    public <ReqVO, ResVO> ResVO excuteMethod(int method, ReqVO requestVO, Class<ResVO> responseType) {

        RequestVO annotation = requestVO.getClass().getAnnotation(RequestVO.class);
        if (annotation == null || annotation.methodType() != method) {
            throw new ClassCastException("method is not same.");
        }


        Service service = mRetrofitRequester.getRetrofit().create(Service.class);

        switch (method) {
            case RestApiRequester.METHOD_DELETE: {
                if(requestVO instanceof DeleteReqVO){
                    Call<Void> call = service.delete(((DeleteReqVO) requestVO).id);
                    try {
                        Response<Void> response = call.execute();
                        int code = response.code();
                        Void resVO = response.body();
                        Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    throw new ClassCastException("request value object type is not same.");
                }
                break;
            }
            case RestApiRequester.METHOD_GET: {
                if (!UserApi.GetResVO.class.isAssignableFrom(responseType)) {
                    throw new ClassCastException("response value object type is not same.");
                }
                if(requestVO instanceof GetReqVO){
                    Call<UserApi.GetResVO> call = service.get(((GetReqVO) requestVO).id);
                    try {
                        Response<UserApi.GetResVO> response = call.execute();
                        int code = response.code();
                        UserApi.GetResVO resVO = response.body();
                        Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
                        return (ResVO) resVO;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    throw new ClassCastException("request value object type is not same.");
                }
                break;
            }
            case RestApiRequester.METHOD_UPDATE: {
                if (!UserApi.UpdateResVO.class.isAssignableFrom(responseType)) {
                    throw new ClassCastException("response value object type is not same.");
                }
                if(requestVO instanceof UpdateReqVO){
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("name",((UpdateReqVO) requestVO).name);
                    body.put("age",((UpdateReqVO) requestVO).age);
                    body.put("salary",((UpdateReqVO) requestVO).salary);
                    Call<UserApi.UpdateResVO> call = service.put(((UpdateReqVO) requestVO).id, body);
                    try {
                        Response<UserApi.UpdateResVO> response = call.execute();
                        int code = response.code();
                        UserApi.UpdateResVO resVO = response.body();
                        Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
                        return (ResVO) resVO;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    throw new ClassCastException("request value object type is not same.");
                }
                break;
            }
        }
        return null;
    }

    interface Service {

        @GET("{id}")
        Call<GetResVO> get(@Path("id") long id);

        @PUT("{id}")
        Call<UpdateResVO> put(@Path("id") long id, @Body Map<String,Object> body);

        @DELETE("{id}")
        Call<Void> delete(@Path("id") long id);

    }

    /**
     * json type
     * {
     * id : 1
     * }
     */
    @RequestVO(methodType = METHOD_GET)
    public static class GetReqVO {
        private long id;

        public GetReqVO(long id) {
            this.id = id;
        }
    }

    /**
     * json type
     * {
     * id : 1
     * name : "asd"
     * age : 20
     * salary : 200.0
     * }
     */
    @RequestVO(methodType = METHOD_UPDATE)
    public static class UpdateReqVO {
        private long id;
        private String name;
        private int age;
        private double salary;

        public UpdateReqVO(long id, String name, int age, double salary) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }

    /**
     * json type
     * {
     * id : 1
     * }
     */
    @RequestVO(methodType = METHOD_DELETE)
    public static class DeleteReqVO {
        private long id;

        public DeleteReqVO(long id) {
            this.id = id;
        }
    }

    /**
     * json type
     * {
     * id : 1
     * name : "asd"
     * age : 20
     * salary : 200.0
     * }
     */
    public static class GetResVO {
        private long id;
        private String name;
        private int age;
        private double salary;

        @Override
        public String toString() {
            return "GetResVO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }

    /**
     * json type
     * {
     * id : 1
     * name : "asd"
     * age : 20
     * salary : 200.0
     * }
     */
    public static class UpdateResVO {
        private long id;
        private String name;
        private int age;
        private double salary;

        @Override
        public String toString() {
            return "UpdateResVO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }

}
