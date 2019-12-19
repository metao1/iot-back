package com.iot.utils;

import org.modelmapper.ModelMapper;

public class WebUtils<T> {

    private final Class<T> typeParameterClass;

    public WebUtils(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T convertToObject(Object object) {
        System.out.println(typeParameterClass.getName());
        return new ModelMapper().map(object, typeParameterClass);
    }

}
