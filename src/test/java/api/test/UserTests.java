package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    Logger logger;


    @BeforeClass
    public void setUpData(){

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());

        //logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void createUser(){

        logger.info("Creating user");

        Response response = UserEndPoints.createUser(this.userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("User created");
    }

    @Test(priority = 2)
    public void getUserDetails(){

        logger.info("Reading user info");

        Response response = UserEndPoints.getUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("User info is displayed");
    }

    @Test(priority = 3)
    public void updateUserDetails(){

        faker = new Faker();
        User userUpdatedPayload = new User();

        userUpdatedPayload.setFirstname(faker.name().firstName());
        userUpdatedPayload.setLastname(faker.name().lastName());
        userUpdatedPayload.setPassword(faker.internet().password());

        logger.info("Update user info");

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userUpdatedPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("User info updated");

    }

    @Test(priority = 4)
    public void deleterUser(){

        logger.info("Delete user");

        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("User is deleted");
    }

}
