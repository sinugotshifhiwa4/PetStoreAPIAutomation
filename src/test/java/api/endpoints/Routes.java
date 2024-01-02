package api.endpoints;

public class Routes {



    public static String base_url = "https://petstore.swagger.io/v2";


    //user
    public static String userPost_url = base_url+ "/user";
    public static String userGet_url = base_url+ "/user/{username}";
    public static String userUpdate_url = base_url+ "/user/{username}";
    public static String userDelete_url = base_url+ "/user/{username}";


    //pet
    public static String petPost_url = base_url+ "/pet";
    public static String petGet_url = base_url+ "/pet/{id}";
    public static String petUpdate_url = base_url+ "/pet/{id}";
    public static String petDelete_url = base_url+ "/pet/{id}";

    //store
    public static String storePost_url = base_url+ "/store/order";
    public static String storeGet_url = base_url+ "/store/order/{id}";
    public static String storeDelete_url = base_url+ "/store/order/{id}";
}
