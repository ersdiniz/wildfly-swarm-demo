package com.diniz.dto;

import java.util.HashMap;

@SuppressWarnings("serial")
public class DynamicDto extends HashMap<String, Object> {

    private DynamicDto() {
    }

    public static DynamicDto of() {
        return new DynamicDto();
    }

    public DynamicDto with(final String key, final Object value) {
        put(key, value);
        return this;
    }
}