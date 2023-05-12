package com.borrow.system.appusermanagement.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils<T> {
    public T copyNonNullProperties(T source, T destination) {
        if(source == null || destination == null || !source.getClass().equals(destination.getClass())) {
            return null;
        }
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dst = new BeanWrapperImpl(destination);

        for(Field property : source.getClass().getDeclaredFields()) {
            Object sourceProperty = src.getPropertyValue(property.getName());
            if (sourceProperty != null && !(sourceProperty instanceof Collection<?>)) {
                dst.setPropertyValue(property.getName(), sourceProperty);
            }
        }

        return destination;
    }

}
