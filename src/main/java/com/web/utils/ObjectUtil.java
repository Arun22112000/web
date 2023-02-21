package com.web.utils;



import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class ObjectUtil {



    private ObjectUtil() throws IllegalAccessException {
        throw new  IllegalAccessException("Illegal Access Exception");

    }


    public static Object copyOnlyNullPropertyOfDestination(final Object destination, final Object source)
            throws InvocationTargetException, IllegalAccessException {
        final BeanUtilsBean beanUtilsBean = new NullBeanUtils();
        beanUtilsBean.copyProperties(source, destination);
        beanUtilsBean.copyProperties(destination, source);
        return destination;
    }

    static class NullBeanUtils extends BeanUtilsBean {
        @Override
        public void copyProperty(final Object dest, final String name, final Object value)
                throws IllegalAccessException, InvocationTargetException {
            if (value == null) {
                return;
            }
            super.copyProperty(dest, name, value);
        }
    }
}