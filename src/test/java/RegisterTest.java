import dto.SuccessfulRegRequest;
import dto.SuccessfulRegResp;
import dto.UnsuccessfulRegResp;
import dto.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {

    public static String BASE_URI = "https://reqres.in";

    @Test

    public void successfulRegistration() {
       public static void InstallSpecification (RequestSpecification request, ResponseSpecification response ){
            RestAssured.requestSpecification = request;
            RestAssured.responseSpecification = response;

        }

        SuccessfulRegRequest request = new SuccessfulRegRequest("eve.holt@reqres.in", "pistol");
        SuccessfulRegResp response = given().baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/register")
                .then().log().all()
                .statusCode(200)
                .extract().body().as(SuccessfulRegResp.class);
        assertEquals(4, response.getId());
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        assertTrue(response.getToken().endsWith("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void registrationWithoutEmail() {

        SuccessfulRegRequest request = new SuccessfulRegRequest("", "pistol");
        UnsuccessfulRegResp response = given().baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/register")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(UnsuccessfulRegResp.class);
        assertEquals("Missing email or username", response.getError());
        assertTrue(response.getError().contains("Missing email or username"));


    }


    @Test
    public void registrationWithoutPassword() {

        SuccessfulRegRequest request = new SuccessfulRegRequest("eve.holt@reqres.in", "");
        UnsuccessfulRegResp response = given().baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/register")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(UnsuccessfulRegResp.class);
        assertEquals("Missing password", response.getError());
    }


    @Test
    public void registrationWithoutPasswordAndEmail() {

        SuccessfulRegRequest request = new SuccessfulRegRequest("", "");
        UnsuccessfulRegResp response = given().baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/register")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(UnsuccessfulRegResp.class);
        assertEquals( "Missing email or username",response.getError());
    }



}


