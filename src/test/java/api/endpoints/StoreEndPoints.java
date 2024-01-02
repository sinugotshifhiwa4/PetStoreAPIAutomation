package api.endpoints;

import api.payload.Store;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreEndPoints {


    public static Response postPlaceOrder(Store payload){

        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)

                .when()
                .post(Routes.storePost_url);

        return response;
    }

    public static Response getOrderPlaced(int oderId){

        Response response = given()
                .pathParam("id", oderId)

                .when()
                .get(Routes.storeGet_url);

        return response;
    }

    public static Response deleteOrderPlaced(int oderId){

        Response response = given()
                .pathParam("id", oderId)

                .when()
                .delete(Routes.storeDelete_url);

        return response;
    }
}
