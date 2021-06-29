package com.krab1.cucumberstf.cucumber.steps;

import com.krab1.cucumberstf.cucumber.Data;
import com.krab1.cucumberstf.domain.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.TimeUnit;

public class StepDefinition {
    private final Data data;

    public StepDefinition(Data data){
        this.data = data;
    }
    @Given("a Test data for {field}")
    public void testStepGiven(JsonPath path) throws InterruptedException {
        data.setPath(path);
        TimeUnit.SECONDS.sleep(2);
    }
    @When("a Test data has {field}")
    public void testStepWhen(JsonPath path){
        Assertions.assertTrue(data.getPath() == path);
    }
    @Then("a Test data removed")
    public void testStepThen(){
        data.setPath(null);
        Assertions.assertNull(data.getPath());
    }
}
