package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StoreTests {

    Faker faker;

    Store storePayload;


    @BeforeClass
    public void setupData(){

        faker = new Faker();
        storePayload = new Store();

        storePayload.setId(faker.idNumber().hashCode());
        storePayload.setPetId(faker.idNumber().hashCode());
        storePayload.setQuantity(faker.number().numberBetween(1, 100));


        // Generate a random date within a specified range
        Date randomDate = faker.date().between(new Date(), faker.date().future(365, TimeUnit.DAYS));

        // Format the date for display
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(randomDate);

        storePayload.setShipDate(formattedDate);

    }

    @Test(priority = 1)
    public void testPlaceOrder(){

        Response response = StoreEndPoints.postPlaceOrder(storePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetOrderPlaced(){

        Response response = StoreEndPoints.getOrderPlaced(storePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testDeleteOrderPlaced(){

        Response response = StoreEndPoints.deleteOrderPlaced(storePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
