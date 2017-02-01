package com.ztory.lib.biz;

/**
 * Generic callback interface.
 * Created by jonruna on 2017-02-01.
 */
public interface BizCallback<T> {
    void callback(T param);
}
