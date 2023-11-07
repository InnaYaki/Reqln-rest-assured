import dto.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUsersListTest {
    public static String BASE_URI = "https://reqres.in";

   @Test
    public void getUserList () {

         given().baseUri(BASE_URI)
                .get("/api/users?page=2")
                 .then().log().all();

    }
@Test
    public void getUserListWithResponse () {

        Response response = given().baseUri(BASE_URI)
                .get("/api/users?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
}

      @Test
    public void getListExtractToClass(){
          List<UserData> users = given().baseUri(BASE_URI)
                  .get("/api/users?page=2")
                  .then().log().all()
                  .extract().body().jsonPath().getList("data",UserData.class);
          System.out.println(users.get(0).getId());
          assertEquals(6,users.size());
          assertEquals(7,users.get(0).getId());

          for (UserData user:
               users) {
              assertTrue(user.getId()>0);
              assertTrue(user.getEmail().endsWith("@reqres.in"));
              assertTrue(user.getAvatar().contains("image"));
              assertTrue(user.getAvatar().contains(user.getId().toString()));
          }
      }







}
