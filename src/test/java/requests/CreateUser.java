package requests;

import data.Constants;
import data.EndPoints;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.with;

public class CreateUser extends BaseUserApi {

    private static Logger logger = LogManager.getLogger(CreateUser.class);

    @Setter
    @Getter
    public static class CreateUserReqBody {
        private Integer id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private Integer userStatus;
    }

    @Setter
    @Getter
    public static class CreateUserRespBody {
        private int code;
        private String type;
        private String message;
    }

    public static CreateUserReqBody generateModel() {
        CreateUserReqBody model = new CreateUserReqBody();
        model.setId(faker.number().numberBetween(1000000, 200000000));
        model.setUsername(faker.name().username());
        model.setFirstName(faker.name().firstName());
        model.setLastName(faker.name().lastName());
        model.setEmail(fakeValuesService.bothify("????##??@gmail.com"));
        model.setPassword(faker.bothify("???###???"));
        model.setPhone(faker.phoneNumber().phoneNumber());
        model.setUserStatus(faker.number().numberBetween(100000, 9999999));
        return model;
    }

    public static CreateUserRespBody sendRequest(CreateUserReqBody createUserReqBody) {
        logger.info(Constants.req_url + baseUrl + EndPoints.createUserPath);
        logger.info(Constants.reqBodyField + createUserReqBody.id);
        logger.info(Constants.reqBodyField + createUserReqBody.username);
        logger.info(Constants.reqBodyField + createUserReqBody.firstName);
        logger.info(Constants.reqBodyField + createUserReqBody.lastName);
        logger.info(Constants.reqBodyField + createUserReqBody.email);
        logger.info(Constants.reqBodyField + createUserReqBody.password);
        logger.info(Constants.reqBodyField + createUserReqBody.phone);
        logger.info(Constants.reqBodyField + createUserReqBody.userStatus);
        return with().spec(Specs.commonRequestSpec)
                        .body(gson.toJson(createUserReqBody, CreateUserReqBody.class))
                        .when()
                        .request("POST", baseUrl + EndPoints.createUserPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(CreateUserRespBody.class);
    }
}
