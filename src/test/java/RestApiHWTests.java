import io.restassured.response.Response;
import models.LombokModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.UserSpecs.*;

public class RestApiHWTests {
    @Test
    void checkStatusCodeTest() {
        Response response = step("Make request", ()->
        given(userRequestSpecific)
                .when()
                .get("/api/users?page=2"));
        step("Check response", ()->{
            response.then()
                    .spec(userResponseSpecification200);
        });
    }

    @Test
    void checkSingleUserTest() {
        LombokModel response =step("Make request", ()->
                given(userRequestSpecific)
                .when()
                    .get("/api/users/2")
                .then()
                    .spec(userResponseSpecificationJson200)
                .extract().as(LombokModel.class));
        step("Check response", ()-> {
        assertEquals(2, response.getData().getId());
        assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
        assertEquals("Janet", response.getData().getFirst_name());
        assertEquals("Weaver", response.getData().getLast_name());
        });
    }
    @Test
    void checkSingleUserNotFoundTest() {
        LombokModel response = step("Make request", ()->
                given(userRequestSpecific)
                .when()
                    .get("/api/users/23")
                .then()
                    .spec(userResponseSpecification404)
                .extract().as(LombokModel.class));
        step("Check response", ()-> {
        assertNull(response.getData());
        assertNull(response.getSupport());
        });
    }
    @Test
    void checkCreateTest() {
        LombokModel authData = new LombokModel();
        authData.setData(new LombokModel.Data());
        authData.setJob("leader");
        authData.setName("morpheus");

        LombokModel response = step("Make request", ()->
                given(userRequestSpecificMax)
                    .body(authData)
                .when()
                    .post("/api/users")
                .then()
                    .spec(userResponseSpecification201)
                .extract().as(LombokModel.class));
        step("Check response", ()-> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });

    }
    @Test
    void checkUpdateTest() {
        LombokModel authData = new LombokModel();
        authData.setData(new LombokModel.Data());
        authData.setJob("zion resident");
        authData.setName("morpheus");
        LombokModel response = step("Make request", ()->
                given(userRequestSpecificMax)
                    .body(authData)
                .when()
                    .put("/api/users/2")
                .then()
                    .spec(userResponseSpecification200)
                .extract().as(LombokModel.class));
        step("Check response", ()->{
        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());
        assertTrue( response.getUpdatedAt().startsWith(String.valueOf(LocalDate.now())));
        });

    }

}
