package com.ztory.lib.biz;

/**
 * Generic Exception class with an optional Object payload.
 * Created by jonruna on 2017-02-01.
 */
public class BizException extends Exception {

    protected final Object mPayload;

    public BizException(String message) {
        super(message);
        mPayload = null;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        mPayload = null;
    }

    public BizException(String message, Object payload) {
        super("payload: " + payload + " | " + message);
        mPayload = payload;
    }

    public BizException(String message, Object payload, Throwable cause) {
        super("payload: " + payload + " | " + message, cause);
        mPayload = payload;
    }

    public BizException(Object payload, Throwable cause) {
        super("payload: " + payload + " | " + cause.getMessage(), cause);
        mPayload = payload;
    }

    public Object getPayload() {
        return mPayload;
    }
}
