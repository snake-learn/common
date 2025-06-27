package com.snake.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TimeZone;

@UtilityClass
public class JsonUtils {

    private static class InstanceHolder {

        private static final ObjectMapper INSTANCE = JsonMapper.builder()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(Feature.ALLOW_COMMENTS)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .defaultTimeZone(TimeZone.getDefault())
                .addModule(new JavaTimeModule())
                .addModule(new SimpleModule().addDeserializer(
                                String.class,
                                new StringWithoutSpaceDeserializer(String.class)
                        )
                )
                .addModule(new SimpleModule().addDeserializer(
                                LocalDateTime.class,
                                new DateTimeDeserializer(LocalDateTime.class)
                        )
                )
                .build();

        private static class StringWithoutSpaceDeserializer extends StdDeserializer<String> {

            public StringWithoutSpaceDeserializer(Class<String> vc) {
                super(vc);
            }

            @Override
            public String deserialize(JsonParser p, DeserializationContext deserializationContext)
                    throws IOException {
                return StringUtils.trim(p.getText());
            }
        }

        private static class DateTimeDeserializer extends StdDeserializer<LocalDateTime> {

            public DateTimeDeserializer(Class<LocalDateTime> vc) {
                super(vc);
            }

            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)
                    throws IOException {
                return DateTimeUtils.toLocalDateTime(p.getText());
            }
        }
    }

    public static ObjectMapper getObjectMapperInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return getObjectMapperInstance().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    String.format("Exception when parsing [JSON=%s] to %s", json, clazz.getSimpleName()), e);
        }
    }

    public static <T> String toJson(T data) {
        try {
            return getObjectMapperInstance().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    String.format("Exception when parsing [DATA=%s] to JSON", data), e);
        }
    }

    public <T> T fromJson(TypeReference<T> typeReference, String json) {
        try {
            return getObjectMapperInstance().readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    String.format("Exception when parsing [JSON=%s] to %s", json, TypeReference.class.getSimpleName())
                    , e
            );
        }
    }

    public static String prettyWrite(String s) {
        try {
            JsonNode jsonNode = getObjectMapperInstance().readTree(s);
            return getObjectMapperInstance().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    String.format("Exception when writing pretty [DATA=%s] to JSON", s), e);
        }
    }

    public static String prettyWrite(byte[] content) {
        try {
            JsonNode jsonNode = getObjectMapperInstance().readTree(content);
            if (jsonNode.isObject()) {
                ((ObjectNode) jsonNode).remove("password");
            }
            return getObjectMapperInstance().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception when writing pretty JSON");
        }
    }
}
