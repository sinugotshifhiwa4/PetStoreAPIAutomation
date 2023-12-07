package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDataDrivenTests {


    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String username, String fname, String lname, String useremail, String password, String phone){


        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstname(fname);
        userPayload.setLastname(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserDetailsByName(String UserName){

        Response response = UserEndPoints.getUser(UserName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String UserName){

        Response response = UserEndPoints.deleteUser(UserName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
