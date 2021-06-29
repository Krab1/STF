package com.krab1.cucumberstf.cucumber.steps;

import com.krab1.cucumberstf.domain.JsonField;
import com.krab1.cucumberstf.domain.JsonPath;
import io.cucumber.java.ParameterType;

public class CustomSteps {

    @ParameterType(".*")
    public JsonPath field(String field) { return JsonField.valueOf(field, CustomField.values()); }
}
