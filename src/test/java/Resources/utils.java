package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecificarion() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
//        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        if(req==null) {
            req = new RequestSpecBuilder().setBaseUri(getGlobalVAlues("baseURI"))
                    .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
        else {
            return req;
        }
    }

    public static String getGlobalVAlues(String key) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Nidhi Upreti\\IdeaProjects\\Rest\\src\\test\\java\\Resources\\GlobalValues.properties");
        Properties p = new Properties();
        p.load(fis);
        System.out.println(p.getProperty("baseURI")+"      printed");
        return p.getProperty(key);

    }
}
