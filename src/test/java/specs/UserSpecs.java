package specs;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class UserSpecs {
    public static RequestSpecification userRequestSpecific = with()
            .baseUri("https://reqres.in");
    public static  ResponseSpecification userResponseSpecification404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();
    public static  ResponseSpecification userResponseSpecification201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
    public static  ResponseSpecification userResponseSpecification200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
    public static  ResponseSpecification userResponseSpecificationJson200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(JSON)
            .log(STATUS)
            .log(BODY)
            .build();
    public static RequestSpecification userRequestSpecificMax = with()
            .baseUri("https://reqres.in")
            .contentType(JSON)
            .log().uri();
}


