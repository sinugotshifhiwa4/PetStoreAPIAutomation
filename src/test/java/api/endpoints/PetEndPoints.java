package api.endpoints;

import api.payload.Pet;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response addNewPet(Pet payload){

        io.restassured.response.Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)

                .when()
                .post(Routes.petPost_url);

        return response;
    }


    public static Response getPet(int petId){

        Response response = given()
                .pathParam("id", petId)

                .when()
                .get(Routes.petGet_url);

        return response;
    }

    public static Response updatePet(int petId, Pet payload){

        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
                .pathParam("id", petId)

                .when()
                .put(Routes.petUpdate_url);

        return response;
    }

    public static Response deletePet(int petId){

        Response response = given()
                .pathParam("id", petId)

                .when()
                .delete(Routes.petDelete_url);

        return response;
    }
}
