package com.tou4u.interfacerestapi.restapi;

import com.tou4u.interfacerestapi.restapi.base.RestApiBaseRequester;

/**
 * http://www.example.com/customers/{cid} rest api
 */
public class CustomerAPI extends RestApiBaseRequester {

    public CustomerAPI() {
        super(" http://www.example.com/customers/{cid}");
    }

    @RequestVO(methodType = METHOD_GET)
    public static class GetReqVO {
        @Argument
        public final int cid;
        @Parameter
        public final int age;

        public GetReqVO(int cid, int age) {
            this.cid = cid;
            this.age = age;
        }
    }

    @RequestVO(methodType = METHOD_POST)
    public static class PostReqVO {
        @Argument
        public final int cid;
        @Parameter
        public final int age;

        public PostReqVO(int cid, int age) {
            this.cid = cid;
            this.age = age;
        }
    }

    @RequestVO(methodType = METHOD_UPDATE)
    public static class UpdateReqVO {
        @Argument
        public final int cid;
        @Parameter
        public final int age;

        public UpdateReqVO(int cid, int age) {
            this.cid = cid;
            this.age = age;
        }
    }

    @RequestVO(methodType = METHOD_DELETE)
    public static class DeleteReqVO {
        @Argument
        public final int cid;

        public DeleteReqVO(int cid) {
            this.cid = cid;
        }
    }
}
