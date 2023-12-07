package api.endpoints;

public class Routes {



    public static String base_url = "https://petstore.swagger.io/v2";


    //user
    public static String userPost_url = base_url+ "/user";
    public static String userGet_url = base_url+ "/user/{username}";
    public static String userUpdate_url = base_url+ "/user/{username}";
    public static String userDelete_url = base_url+ "/user/{username}";
}
