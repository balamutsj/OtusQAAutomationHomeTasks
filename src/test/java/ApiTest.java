import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.CreateUser;
import requests.GetUser;

public class ApiTest extends BaseTest{

    @Test
    void createUser() {
        /*
        * Тест-кейс
        * 1. Отправить запрос на создание юзера
        * Проверить: тело ответа содержит статус-код 200, тип: неизвестный, сообщение не пустое
        * */
        CreateUser.CreateUserReqBody reqBody = CreateUser.generateModel();
        CreateUser.CreateUserRespBody respBody = CreateUser.sendRequest(reqBody);
        Assertions.assertEquals(respBody.getCode(), 200);
        Assertions.assertEquals(respBody.getType(), "unknown");
        Assertions.assertNotNull(respBody.getMessage());
    }

    @Test
    void createUserError() {
        /*
         * Тест-кейс
         * 1. Отправить запрос на создание юзера
         * Проверить: тело ответа содержит статус-код 200, тип: неизвестный, сообщение не пустое
         * */
        CreateUser.CreateUserReqBody reqBody = new CreateUser.CreateUserReqBody();
        CreateUser.CreateUserRespBody respBody = CreateUser.sendRequest(reqBody);
        Assertions.assertEquals(respBody.getCode(), 200);
        Assertions.assertEquals(respBody.getType(), "unknown");
        Assertions.assertEquals(respBody.getMessage(), "0");
    }

    @Test
    void getUser() {
        /*
        * Тест-кейс
        * 1. Отправить запрос на создание юзера
        * 2. Отправить запрос на получение данных юзера по имени юзера
        * Проверить: поле id указанное при создании юзера = полю id полученного юзера
        * */
        CreateUser.CreateUserReqBody reqBody = CreateUser.generateModel();
        CreateUser.sendRequest(reqBody);
        GetUser.GetUserRespBody respBody = GetUser.sendRequest(reqBody.getUsername(), GetUser.GetUserRespBody.class);
        Assertions.assertEquals(reqBody.getId(), respBody.getId());
    }

    /*
    * Тест-кейс
    * 1. Отправить запрос на получение несуществующего юзера
    * Проверить: что пользователя не существует
    * */
    @Test
    void getNotExistingUser() {
        GetUser.GetUserErrRespBody respBody = GetUser.sendRequest("someStrangeNotExistingUserName", GetUser.GetUserErrRespBody.class, 404);
        Assertions.assertEquals(respBody.getMessage(), "User not found");
    }
}
