import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RestApiHWTests {
    @Test
    void checkStatusCodeTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
    @Test
    void checkSingleUserTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .contentType(JSON)
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }
    @Test
    void checkSingleUserNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body(equalTo("{}"));
    }
    @Test
    void checkCreateTest() {
        String authData = "{\n\"name\": \"morpheus\",\n\"job\": \"leader\"\n}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void checkUpdateTest() {
        String authData = "{\n\"name\": \"morpheus\",\n\"job\": \"zion resident\"\n}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .put("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body( "updatedAt", startsWith(String.valueOf(LocalDate.now())));
    }

}
