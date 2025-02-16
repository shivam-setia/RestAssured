package StepDefinition;

import io.cucumber.java.Before;
import io.cucumber.plugin.event.StepDefinition;

import java.io.IOException;

public class Hooks {
    @Before("@deletePlace")
    public void beforeScenario() throws IOException {
        stepDefinition method = new stepDefinition();
        if(stepDefinition.place_id==null){
            method.add_place_paylod_with("nidhi","Vaishali","Java");
            method.user_calls_with_https_request("AddPlaceAPI","POST");
            method.verify_if_place_id_created_maps_to_using("nidhi","getPlaceAPI");
        }
    }
}
