package com.ztory.lib.biz;

/**
 * Generic callback interface with a return type.
 * Created by jonruna on 2017-02-01.
 */
public interface BizCallbackProduce<T, D> {
    T callbackProduce(D param) throws Exception;
}
