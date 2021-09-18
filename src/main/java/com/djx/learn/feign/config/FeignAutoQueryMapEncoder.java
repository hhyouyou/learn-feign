package com.djx.learn.feign.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import feign.QueryMapEncoder;
import feign.codec.EncodeException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 把@RequestObject对象编码为查询参数Map对象(MethodMetadata.queryMapIndex是唯一可以自定义对象编码的契机了)
 *
 * @author djx
 */
public class FeignAutoQueryMapEncoder implements QueryMapEncoder {

    private final ConcurrentHashMap<Class<?>, List<Field>> fieldMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> encode(Object object) {
        try {
            Class<?> clazz = object.getClass();
            List<Field> fieldList = fieldMap.computeIfAbsent(clazz, this::fieldList);

            Map<String, Object> map = new HashMap<>(fieldList.size());
            for (Field field : fieldList) {
                Object fieldObj = field.get(object);
                if (fieldObj == null) {
                    continue;
                }
                String name;
                // 支持@JsonProperty
                if (field.getDeclaredAnnotation(JsonProperty.class) != null) {
                    name = field.getDeclaredAnnotation(JsonProperty.class).value();
                } else {
                    name = field.getName();
                }
                map.put(name, fieldObj);
            }
            return map;
        } catch (IllegalAccessException e) {
            throw new EncodeException("Fail encode ParamObject into query Map", e);
        }
    }

    private List<Field> fieldList(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            fields.add(field);
        }
        return fields;
    }

}