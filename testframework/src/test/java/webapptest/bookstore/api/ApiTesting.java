package webapptest.bookstore.api;

import com.krab.stfbase.api.pojos.BookImpl;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import webapptest.bookstore.baseclass.TestBoot;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTesting extends TestBoot {

    public RequestSpecification getBuilder(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setConfig(newConfig()
                        .encoderConfig(encoderConfig()
                                .defaultContentCharset(StandardCharsets.UTF_8))).build();
    }

    @Test(description = "Testing with restAssured without deserialisation")
    public void plainRestGetAllTest() {
        Response response = RestAssured.given(getBuilder()).get("/api/v1/books/all");
        List<Map<String, Object>> result = response.getBody().as(new TypeRef<>() { });
        assertThat(response).as("Response result").extracting(ResponseOptions::getStatusCode).isEqualTo(200);
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(response).as("Response result").extracting(ResponseOptions::getStatusCode).isEqualTo(200);
            soft.assertThat(result).as("Checking if we recieved any data from request").isNotNull().isNotEmpty().hasSizeGreaterThan(0);
        });
    }

    @Test(description = "Testing with restAssured with deserialisation")
    public void plainRestGetAllWithDeserialisationTest() {
        Response response = RestAssured.given(getBuilder()).get("/api/v1/books/all");
        List<BookImpl> result = response.getBody().as(new TypeRef<>() {});
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(response).as("Response result").extracting(ResponseOptions::getStatusCode).isEqualTo(200);
            soft.assertThat(result).as("Checking if we recieved any data from request").isNotNull().isNotEmpty().hasSizeGreaterThan(0);
        });
    }
}
