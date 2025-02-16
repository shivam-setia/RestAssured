package Resources;

import POJO.AddPlace;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String x,String y,String z){
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(y);
        p.setLanguage(z);
        p.setPhoneNumber("(+91) 874 556 6789");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(x);

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shoe");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38);
        l.setLng(33);
        p.setLocation(l);
        return p;
    }
    public String deletePayload(String place_id){
        return "{\r\n     \"place_id\": \""+place_id+"\"\r\n}";

    }
}
