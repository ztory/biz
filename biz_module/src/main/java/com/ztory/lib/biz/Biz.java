package com.ztory.lib.biz;

import java.util.HashMap;
import java.util.Map;

/**
 * Biz is used to set or get types related to business-rules.
 * Created by jonruna on 2017-02-01.
 */
public class Biz {

    private static final Map<Class, Object> sInstanceMap = new HashMap<>(10);

    public static <T> void set(Class<T> clazz, T instance) {
        synchronized (sInstanceMap) {
            sInstanceMap.put(clazz, instance);
        }
    }

    public static <T> T get(Class<T> clazz) {
        synchronized (sInstanceMap) {
            return (T) sInstanceMap.get(clazz);
        }
    }

    public static <T> T castOrNull(Class<T> clazz, Object object) {
        if (object == null) {
            return null;
        }
        else if (!clazz.isAssignableFrom(object.getClass())) {
            return null;
        }
        return (T) object;
    }

}
