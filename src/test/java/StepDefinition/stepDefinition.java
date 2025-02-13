package StepDefinition;

import POJO.AddPlace;
import POJO.Location;
import Resources.TestDataBuild;
import Resources.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinition extends utils {
    RequestSpecification resp;
    ResponseSpecification res;
    Response response;
    @Given("Add place paylod with {string} {string}  {string}")
    public void add_place_paylod_with(String name, String address, String lang) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        TestDataBuild data = new TestDataBuild();
        resp = given().spec(requestSpecificarion()).body(data.addPlacePayload(name, address, lang)).log().all();


    }

    @When("user calls {string} with post https request")
    public void user_calls_with_post_https_request(String string) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        try {
            res = new ResponseSpecBuilder().expectStatusCode(200)
                    .expectContentType(ContentType.JSON).build();
            response = resp.when().post("/maps/api/place/add/json").then().spec(res).log().all().extract().response();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expvalue) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        String Resp = response.getBody().asString();
        JsonPath js = new JsonPath(Resp);
        assertEquals(js.get(keyValue).toString(),Expvalue);

    }


}

