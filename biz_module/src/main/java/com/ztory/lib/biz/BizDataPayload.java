package com.ztory.lib.biz;

/**
 * Generic interface for holding typed data and payload instances.
 * Created by jonruna on 2017-02-01.
 */
public interface BizDataPayload<D, P> {
    D getData();
    P getPayload();
}
