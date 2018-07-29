package com.tou4u.interfacerestapi.restapi.base;

/**
 * one resource - one object
 * one resource - at most 4 method(GET, POST, UPDATE, DELETE)
 */
public interface RestApiRequester<GetReqVO, PostReqVO, UpdateReqVO, DeleteReqVO> {

    public <GetResVO> GetResVO excuteGet(GetReqVO requestVO, Class<GetResVO> responseType);

    public <PostResVO> PostResVO excutePost(PostReqVO requestVO, Class<PostResVO> responseType);

    public <UpdateResVO> UpdateResVO excuteUpdate(UpdateReqVO requestVO, Class<UpdateResVO> responseType);

    public <DeleteResVO> DeleteResVO excuteDelete(DeleteReqVO requestVO, Class<DeleteResVO> responseType);

}
