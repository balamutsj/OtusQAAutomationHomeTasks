package requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specs {
    public static RequestSpecification commonRequestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();


}
