package requests;

import data.Constants;
import data.EndPoints;
import io.restassured.http.Header;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.with;

public class GetUser extends BaseUserApi {

    private static Logger logger = LogManager.getLogger(GetUser.class);

    @Setter
    @Getter
    public static class GetUserRespBody {
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
    public static class GetUserErrRespBody {
        private int code;
        private String type;
        private String message;
    }

    public static <T> T sendRequest(String username, Class<T> responseClass, int... statusCode) {
        logger.info(Constants.req_url + baseUrl + EndPoints.getUserPath);
        int responseStatusCode;
        if(statusCode.length > 0) {
            responseStatusCode = statusCode[0];
        } else {
            responseStatusCode = 200;
        }
        return with()
                .spec(Specs.commonRequestSpec)
                .pathParam("username", username)
                .when()
                .request("GET", baseUrl + EndPoints.getUserPath)
                .then()
                .statusCode(responseStatusCode)
                .extract()
                .as(responseClass);
    }
}
