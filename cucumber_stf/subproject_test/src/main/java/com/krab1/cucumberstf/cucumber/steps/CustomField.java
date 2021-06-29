package com.krab1.cucumberstf.cucumber.steps;

import com.krab1.cucumberstf.domain.JsonPath;

public enum CustomField implements JsonPath {

    VALUE("json.path.reference2");

    private final String path;

    CustomField(String path){
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
