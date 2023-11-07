import dto.UserData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetSingleUserTest {

    public static Integer id =5;
    public static String BASE_URI = "https://reqres.in";

    @Test
    public void getSingleUser(){
        UserData user = given().baseUri(BASE_URI)
                .when()
                .get("/api/users/"+id)
                .then().log().all()
                .extract().body().jsonPath().getObject("data",UserData.class);
        assertEquals (id,user.getId());
        assertFalse(user.getEmail().isEmpty());
        assertNotNull(user.getEmail());
        assertTrue(user.getAvatar().contains("image"));
        assertTrue(user.getAvatar().contains("5"));
        assertTrue(user.getEmail().endsWith("@reqres.in"));
    }
}
