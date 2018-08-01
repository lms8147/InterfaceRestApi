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

public class UserApi implements RestApiRequester<UserApi.GetReqVO, Void, UserApi.UpdateReqVO, UserApi.DeleteReqVO> {

    private static String TAG = UserApi.class.getSimpleName();

    private RetrofitRequester mRetrofitRequester;

    public UserApi() {
        mRetrofitRequester = new RetrofitRequester("http://192.168.0.2:8080/api/users/");
    }

    interface Service {

        @GET("{id}")
        Call<GetResVO> get(@Path("id") long id);

        @PUT("{id}")
        Call<UpdateResVO> put(@Path("id") long id, @Body Map<String,Object> body);

        @DELETE("{id}")
        Call<Void> delete(@Path("id") long id);

    }

    @Override
    public <GetResVO> GetResVO excuteGet(GetReqVO requestVO, Class<GetResVO> responseType) {
        Service service = mRetrofitRequester.getRetrofit().create(Service.class);

        if (UserApi.GetResVO.class.isAssignableFrom(responseType)) {
            Call<UserApi.GetResVO> call = service.get(requestVO.id);
            try {
                Response<UserApi.GetResVO> response = call.execute();
                int code = response.code();
                UserApi.GetResVO resVO = response.body();
                Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
                return (GetResVO) resVO;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public <PostResVO> PostResVO excutePost(Void requestVO, Class<PostResVO> responseType) {
        return null;
    }

    @Override
    public <DeleteResVO> DeleteResVO excuteDelete(DeleteReqVO requestVO, Class<DeleteResVO> responseType) {
        Service service = mRetrofitRequester.getRetrofit().create(Service.class);

        Call<Void> call = service.delete(requestVO.id);
        try {
            Response<Void> response = call.execute();
            int code = response.code();
            Void resVO = response.body();
            Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <UpdateResVO> UpdateResVO excuteUpdate(UpdateReqVO requestVO, Class<UpdateResVO> responseType) {
        Service service = mRetrofitRequester.getRetrofit().create(Service.class);

        if (UserApi.UpdateResVO.class.isAssignableFrom(responseType)) {
            HashMap<String, Object> body = new HashMap<>();
            body.put("name",requestVO.name);
            body.put("age",requestVO.age);
            body.put("salary",requestVO.salary);
            Call<UserApi.UpdateResVO> call = service.put(requestVO.id, body);
            try {
                Response<UserApi.UpdateResVO> response = call.execute();
                int code = response.code();
                UserApi.UpdateResVO resVO = response.body();
                Log.d(TAG, String.format("status : %d, data : %s", code, resVO));
                return (UpdateResVO) resVO;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * json type
     * {
     * id : 1
     * }
     */
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
