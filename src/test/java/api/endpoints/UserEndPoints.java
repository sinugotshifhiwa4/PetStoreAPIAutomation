package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;


public class UserEndPoints {


    public static Response createUser(User payload){

        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)

                .when()
                .post(Routes.userPost_url);

        return response;
    }

    public static Response getUser(String userName){

        Response response = given()
                .pathParam("username", userName)

                .when()
                .get(Routes.userGet_url);

        return response;

    }

    public static Response updateUser(String userName, User payload){

        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
                .pathParam("username", userName)

                .when()
                .put(Routes.userUpdate_url);

        return response;
    }

    public static Response deleteUser(String userName){

        Response response = given()
                .pathParam("username", userName)

                .when()
                .delete(Routes.userDelete_url);

        return response;

    }


}
