package StepDefinition;

import POJO.AddPlace;
import POJO.Location;
import Resources.EndPoints;
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
import org.omg.Messaging.SyncScopeHelper;

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
    static String place_id;
    @Given("Add place paylod with {string} {string}  {string}")
    public void add_place_paylod_with(String name, String address, String lang) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        TestDataBuild data = new TestDataBuild();
        resp = given().spec(requestSpecificarion()).body(data.addPlacePayload(name, address, lang)).log().all();
    }

    @When("user calls {string} with {string} https request")
    public void user_calls_with_https_request(String endPoint, String method) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        try {
            EndPoints EndPoint=EndPoints.valueOf(endPoint);  //till here stored value of endpoint from feature file then enum into this variable
            //have to get value of that enum var using method we created inside enum as getEndpoint and pass it instead of endppoint below
            res = new ResponseSpecBuilder().expectStatusCode(200)
                    .expectContentType(ContentType.JSON).build();
            System.out.println(EndPoint.getEndPoint());
            if(method.equalsIgnoreCase("POST")) {
                response = resp.when().post(EndPoint.getEndPoint()).then().spec(res).log().all().extract().response();
            } else if (method.equalsIgnoreCase("GET")) {
                response = req.when().get(EndPoint.getEndPoint()).then().spec(res).extract().response();
            }

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
        assertEquals(getJsonPath(response,keyValue),Expvalue);

    }

    @Then("verify if place_id created maps to {string} using {string}")
    public void verify_if_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //create req spec for get => hit get req => but need place id from response of addplace api
//        => extract place id from response can use above mthod of @!then as jsonpath is already there
        //better to create utility for that too in utils file

        //write complete req here
        place_id = getJsonPath(response,"place_id");
//        System.out.println("place id is ->"+place_id);
        req = given().spec(requestSpecificarion()).queryParam("place_id",place_id);  //till here given part is done
        // now have to call get request but that was automated above based on ,
        // method type just recall that method only
        user_calls_with_https_request(resource,"GET");
        assertEquals(getJsonPath(response,"name"),expectedName);
    }

    @Given("Delete payload")
    public void delete_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        TestDataBuild data = new TestDataBuild();
        resp = given().spec(requestSpecificarion()).body(data.deletePayload(place_id));
//        user_calls_with_https_request(resource,"POST");
    }
@When("user calls {string} with {string} method")
public void user_calls_with_method(String endPoint, String method) {
    // Write code here that turns the phrase above into concrete actions
    try {
            EndPoints EndPoint=EndPoints.valueOf(endPoint);  //till here stored value of endpoint from feature file then enum into this variable
            //have to get value of that enum var using method we created inside enum as getEndpoint and pass it instead of endppoint below
            res = new ResponseSpecBuilder().expectStatusCode(200)
                    .expectContentType(ContentType.JSON).build();
            System.out.println(EndPoint.getEndPoint());
            if(method.equalsIgnoreCase("POST")) {
                response = resp.when().post(EndPoint.getEndPoint()).then().spec(res).log().all().extract().response();
            } else if (method.equalsIgnoreCase("GET")) {
                response = req.when().get(EndPoint.getEndPoint()).then().spec(res).extract().response();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
}
    @Then("the API call is success with status code {string}")
    public void the_api_call_is_success_with_status_code(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(),200);
    }
}

