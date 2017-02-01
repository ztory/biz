package com.ztory.lib.biz;

import org.json.JSONObject;

/**
 * Utility functions related to business logic.
 * Created by jonruna on 2017-02-01.
 */
public class BizUtil {

    public static <D, P> BizDataPayload<D, P> createDataPayload(final D data, final P payload) {
        return new BizDataPayload<D, P>() {
            @Override
            public D getData() {
                return data;
            }

            @Override
            public P getPayload() {
                return payload;
            }
        };
    }

    public static <T> T getJSONvalue(String jsonKey, Class<T> clazz, JSONObject jsonObject) {
        if (clazz == null || jsonObject == null || jsonKey == null) {
            return null;
        }

        if (jsonObject.isNull(jsonKey)) {
            return null;
        }

        try {
            Object jsonValue = jsonObject.get(jsonKey);
            if (clazz.isAssignableFrom(jsonValue.getClass())) {
                return (T) jsonValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
