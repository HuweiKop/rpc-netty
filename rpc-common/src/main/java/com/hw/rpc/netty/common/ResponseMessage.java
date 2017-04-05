package com.hw.rpc.netty.common;

import java.io.Serializable;

/**
 * Created by huwei on 2017/4/5.
 */
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = -1135705671056911830L;

    private String requestId;
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
