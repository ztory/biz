package com.ztory.lib.biz;

/**
 * Generic interface for producing typed data.
 * Created by jonruna on 2017-02-01.
 */
public interface BizProduce<T> {
    T produce() throws Exception;
}
