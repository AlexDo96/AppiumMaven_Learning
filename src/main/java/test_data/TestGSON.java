package test_data;

import com.google.gson.Gson;

public class TestGSON {
    public static void main(String[] args) {
        // Convert from JSON to Object
        String jsonObject = "{\n" +
                "  \"username\": \"teo@sth.com\",\n" +
                "  \"password\": \"123456789\"\n" +
                "}";
        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject, LoginCreds.class);
        System.out.println(loginCreds);

        System.out.println(gson.toJson(loginCreds));
    }
}
