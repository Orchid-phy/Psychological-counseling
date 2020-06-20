package com.orchid.counselling.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Object与其他类型的转换
 */
public class ObjectChange {

    /**
     * 将object转换为list
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz){

        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>){
            for (Object o : (List<?>) obj){
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
