package com.tou4u.interfacerestapi.restapi;

import com.tou4u.interfacerestapi.restapi.base.RestApiBaseRequester;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.example.com/customers/{cid} rest api
 *
 */
public class CustomerAPI extends RestApiBaseRequester<CustomerAPI.GetReqVO, CustomerAPI.PostReqVO, CustomerAPI.UpdateReqVO, CustomerAPI.DeleteReqVO> {

    @Override
    public <GetResVO> GetResVO excuteGet(GetReqVO requestVO, Class<GetResVO> responseType) {
        String specificURL = mBaseURL.replaceFirst("uid","" + requestVO.id);
        Map<String, Object> params = new HashMap<>();
        params.put("age",requestVO.age);
        return requestVOWithGSON(specificURL, params, responseType);
    }

    @Override
    public <PostResVO> PostResVO excutePost(PostReqVO requestVO, Class<PostResVO> responseType) {
        String specificURL = mBaseURL.replaceFirst("uid","" + requestVO.id);
        Map<String, Object> params = new HashMap<>();
        params.put("age",requestVO.age);
        return requestVOWithGSON(specificURL, params, responseType);
    }

    @Override
    public <UpdateResVO> UpdateResVO excuteUpdate(UpdateReqVO requestVO, Class<UpdateResVO> responseType) {
        String specificURL = mBaseURL.replaceFirst("uid","" + requestVO.id);
        Map<String, Object> params = new HashMap<>();
        params.put("age",requestVO.age);
        return requestVOWithGSON(specificURL, params, responseType);
    }

    @Override
    public <DeleteResVO> DeleteResVO excuteDelete(DeleteReqVO requestVO, Class<DeleteResVO> responseType) {
        String specificURL = mBaseURL.replaceFirst("uid","" + requestVO.id);
        return requestVOWithGSON(specificURL, null, responseType);
    }

    public CustomerAPI() {
        super(" http://www.example.com/customers/{cid}");
    }

    public static class GetReqVO{
        public int id;
        public int age;
    }

    public static class PostReqVO{
        public int id;
        public int age;
    }

    public static class UpdateReqVO{
        public int id;
        public int age;
    }

    public static class DeleteReqVO{
        public int id;
    }
}
