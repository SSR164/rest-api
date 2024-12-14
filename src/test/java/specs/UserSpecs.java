package specs;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class UserSpecs {

    // Базовая спецификация для запросов
    public static final RequestSpecification userRequestSpecification = with()
            .contentType(JSON)
            .filter(new AllureRestAssured())
            .log().all();

    // Универсальный метод для ResponseSpecification
    public static ResponseSpecification createResponseSpecification(int statusCode, boolean expectJson) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY);

        // Если ожидаем JSON, добавляем соответствующую проверку
        if (expectJson) {
            builder.expectContentType(JSON);
        }

        return builder.build();
    }

    // Примеры использования
    public static final ResponseSpecification userResponseSpecification200 = createResponseSpecification(200, false);
    public static final ResponseSpecification userResponseSpecification201 = createResponseSpecification(201, false);
    public static final ResponseSpecification userResponseSpecification404 = createResponseSpecification(404, false);
    public static final ResponseSpecification userResponseSpecificationJson200 = createResponseSpecification(200, true);
}