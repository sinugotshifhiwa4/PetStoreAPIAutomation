package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PetTests {

    Faker faker;

    Pet petPayload;


    @BeforeClass
    public void setUpData(){

        faker = new Faker();

        petPayload = new Pet();


        petPayload.setId(faker.idNumber().hashCode());
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(Collections.singletonList(faker.internet().url()));

        // Set category (you need to create a Category object and set its properties)
        Category category = new Category();
        category.setId(faker.idNumber().hashCode());
        category.setName(faker.animal().name());
        petPayload.setCategory(category);

        // Set tags (you need to create a Tag object and set its properties)
        Tag tag = new Tag();
        tag.setId(faker.idNumber().hashCode());
        tag.setName(faker.animal().name());
        petPayload.setTags(List.of(tag));

        // Set status to one of "available," "pending," or "sold"
        String[] statusOptions = {"available", "pending", "sold"};
        petPayload.setStatus(statusOptions[faker.number().numberBetween(0, statusOptions.length)]);

    }

    @Test(priority = 1)
    public void addNewPet(){

        Response response = PetEndPoints.addNewPet(this.petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "addNewPet")
    public void getPetDetails(){

        Response response = PetEndPoints.getPet(petPayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void updatePet(){

        faker = new Faker();

        petPayload.setName(faker.animal().name());

        Response response = PetEndPoints.updatePet(petPayload.getId(), this.petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void deletePet(){

        Response response = PetEndPoints.deletePet(petPayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
