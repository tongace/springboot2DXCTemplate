package com.dxc.application.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private JsonUtil() {}

	private static final ObjectMapper OBJECT_MAPPER_SINGLETON = new ObjectMapper();

	public static String toJsonString(final Object object) {
		try {
            OBJECT_MAPPER_SINGLETON.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			OBJECT_MAPPER_SINGLETON.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
			return OBJECT_MAPPER_SINGLETON.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			return String.valueOf(object);
		}
	}

	public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
		T obj = null;
		try {
			obj = OBJECT_MAPPER_SINGLETON.readValue(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
