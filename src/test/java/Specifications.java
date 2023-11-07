import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.net.URI;

public class Specifications {

    public static  RequestSpecification requestSpecification (String URI){
    return new RequestSpecBuilder().setBaseUri(URI)
        .setContentType(ContentType.JSON)
        .build();
    }
    public static ResponseSpecification resp400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }


    public static ResponseSpecification resp200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void installSpecification(RequestSpecification req, ResponseSpecification resp){
        RestAssured.requestSpecification = req;
        RestAssured.responseSpecification = resp;

    }


}
