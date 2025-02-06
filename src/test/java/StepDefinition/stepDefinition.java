package StepDefinition;

import POJO.AddPlace;
import POJO.Location;
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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinition {
    RequestSpecification resp;
    ResponseSpecification res;
    Response response;
    @Given("Add place paylod")
    public void add_place_paylod() throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29 , side layout cohen 09");
        p.setLanguage("French-IN");
        p.setPhoneNumber("(+91) 874 556 6789");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shoe");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38);
        l.setLng(33);
        p.setLocation(l);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(p);

// Calculate the length in bytes of the JSON string
        int contentLength = requestBody.getBytes(StandardCharsets.UTF_8).length;

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key","qaclick123").setContentType(ContentType.JSON)
                .build();
        resp = given().spec(req).header("Content-Type", "application/json").body(p).log().all();

        res = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();


    }

    @When("user calls {string} with post https request")
    public void user_calls_with_post_https_request(String string) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        try {
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
//        String responseBody = response.getBody().asString();
//        if (responseBody == null || responseBody.isEmpty()) {
//            throw new RuntimeException("Response body is empty or null");
//        }
//
//        // Then proceed to parse or validate the JSON as required
//        JsonPath jsonPath = new JsonPath(responseBody);
//        String actualStatus = jsonPath.getString("status");
//        assertEquals(actualStatus,value);
    }


}

