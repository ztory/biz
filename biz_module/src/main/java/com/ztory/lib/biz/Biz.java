package com.ztory.lib.biz;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to set or get business-rule-interfaces.
 * Created by jonruna on 23/09/16.
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
//            Object returnObject = sInstanceMap.get(clazz);
//            if (returnObject == null) {
//                return null;
//            }
//            else if (!clazz.isAssignableFrom(returnObject.getClass())) {
//                return null;
//            }
//            return (T) returnObject;
//            return castOrNull(clazz, sInstanceMap.get(clazz));

            // No need to check cast, because set() method only takes class of its own type
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

    /*
    private static BizLevelHandler sLevelHandler;

    public static BizLevelHandler getLevelHandler() {
        return sLevelHandler;
    }

    public static void setLevelHandler(BizLevelHandler theLevelHandler) {
        sLevelHandler = theLevelHandler;
    }
    */

}
