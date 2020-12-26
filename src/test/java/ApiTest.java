import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.CreateUser;
import requests.GetUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Assertions.assertAll(
                () -> assertEquals(respBody.getCode(), 200),
                () -> assertEquals(respBody.getType(), "unknown"),
                () -> assertNotNull(respBody.getMessage())
        );
    }

    @Test
    void createUserError() {
        /*
         * Тест-кейс
         * 1. Отправить запрос на создание юзера с пустым телом
         * Проверить: тело ответа содержит статус-код 200, тип: неизвестный, сообщение равно 0
         * */
        CreateUser.CreateUserReqBody reqBody = new CreateUser.CreateUserReqBody();
        CreateUser.CreateUserRespBody respBody = CreateUser.sendRequest(reqBody);
        Assertions.assertAll(
                () -> assertEquals(respBody.getCode(), 200),
                () -> assertEquals(respBody.getType(), "unknown"),
                () -> assertNotNull(respBody.getMessage(), "0")
        );
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
        assertEquals(reqBody.getId(), respBody.getId());
    }

    @Test
    void getNotExistingUser() {
        /*
         * Тест-кейс
         * 1. Отправить запрос на получение несуществующего юзера
         * Проверить: что пользователя не существует
         * */
        GetUser.GetUserErrRespBody respBody = GetUser.sendRequest("someStrangeNotExistingUserName", GetUser.GetUserErrRespBody.class, 404);
        assertEquals(respBody.getMessage(), "User not found");
    }

    @Test
    void createUserOnlyWithId() {
        /*
         * 1. Отправить запрос на создание юзера только с полем id
         * Проверить: тело ответа содержит статус-код 200, тип: неизвестный, сообщение не пустое
         * */
        CreateUser.CreateUserReqBody reqBody = new CreateUser.CreateUserReqBody.Builder()
                .id(faker.number().numberBetween(1000000, 200000000))
                .build();
        CreateUser.CreateUserRespBody respBody = CreateUser.sendRequest(reqBody);
        Assertions.assertAll(
                () -> assertEquals(respBody.getCode(), 200),
                () -> assertEquals(respBody.getType(), "unknown"),
                () -> assertNotNull(respBody.getMessage())
        );
    }

    @Test
    void createUserWithIdAndUserName() {
        /*
         * 1. Отправить запрос на создание юзера с полем id  и полем
         * Проверить: тело ответа содержит статус-код 200, тип: неизвестный, сообщение не пустое
         * */
        CreateUser.CreateUserReqBody reqBody = new CreateUser.CreateUserReqBody.Builder()
                .id(faker.number().numberBetween(1000000, 200000000))
                .username(faker.name().username())
                .build();
        CreateUser.CreateUserRespBody respBody = CreateUser.sendRequest(reqBody);
        Assertions.assertAll(
                () -> assertEquals(respBody.getCode(), 200),
                () -> assertEquals(respBody.getType(), "unknown"),
                () -> assertNotNull(respBody.getMessage())
        );
    }
}
