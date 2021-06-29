package com.krab1.cucumberstf.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum JsonField implements JsonPath{

    VALUE("json.path.reference");

    private final String path;

    JsonField(String path){
        this.path = path;
    }

    public static <T extends JsonPath> JsonPath valueOf(String field, T[] values){
        Optional<T> jsonField = Stream.of(values)
                .filter(v-> v.name().equals(field))
                .findFirst();
        return  jsonField.isPresent() ? jsonField.get() : valueOf(field);
    }

    @Override
    public String getPath() {
        return path;
    }
}
