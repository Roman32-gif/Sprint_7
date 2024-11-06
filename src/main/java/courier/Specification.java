package courier;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specification {
    protected RequestSpecification requestSpec;

    public Specification() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected RequestSpecification getSpec() {
        return requestSpec;
    }

}
