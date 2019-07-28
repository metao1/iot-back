package com.gro.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

public class ObjectFactory {

    private final ObjectMapper mapper;
    private final ModelMapper modelMapper;

    public ObjectFactory(ModelMapper modelMapper, ObjectMapper mapper) {
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    public <T> String toJson(final TypeReference<T> type, Object object) {
        String data = null;
        try {
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            data = mapper.writer().forType(type).writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public <T> T fromJson(final TypeReference<T> type, final String jsonPacket) {
        T data = null;
        try {
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            data = mapper.reader().forType(type).readValue(jsonPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
