package Resources;

import POJO.AddPlace;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(){
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
        return p;
    }
}
