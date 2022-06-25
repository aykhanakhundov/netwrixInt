package com.netwrix.tests;


import com.github.javafaker.Faker;
import com.netwrix.pages.Base;
import com.netwrix.utilities.ConfigurationReader;
import org.testng.annotations.Test;


import static com.netwrix.utilities.Driver.getDriver;
import static org.testng.Assert.assertEquals;

public class Interview {

    static Base base = new Base();
    static String token;

    static Faker faker = new Faker();

    static String userName = faker.name().firstName();

    static String password = "samplePass20@!%";


    @Test(priority = 1)
    public static void testRegister(){

//        System.out.println("userName = " + userName);
//
//        System.out.println("password = " + password);

        getDriver().get(ConfigurationReader.getProperty("env"));

        String body = "{\n" +
                "  \"username\": \"" + userName + "\",\n" +
                "  \"email\": \"user@example.com\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        base.registerUser(body);

        String actualResult = base.registerSuccessMsg.getText().
                substring(1, base.registerSuccessMsg.getText().length() - 1);

        assertEquals(actualResult, "User created successfully!", "User registration failed !");

        assertEquals(base.responseStatusCode.getText(), "200", "Status code didn't match !");

    }




    @Test(priority = 2)
    public static void testLogin(){

        getDriver().navigate().refresh();

        String body = "{\n" +
                "  \"username\": \"" + userName +"\",\n" +
                "  \"password\": \"" + password +"\"\n" +
                "}";

        token = base.getToken(body);

        System.out.println("token = " + token);

        assertEquals(base.responseStatusCode.getText(), "200", "Status code didn't match !");
    }


    @Test(priority = 3)
    public static void testBaseballList(){

        getDriver().navigate().refresh();

        base.authorize(token);

        base.baseballList.click();

        base.tryItOut.click();

        base.execute.click();

        assertEquals(base.responseStatusCode.getText(), "200", "Status code didn't match !");
    }



    @Test(priority = 4)
    public static void testDeleteRegisteredUser(){

        getDriver().navigate().refresh();

        String body = "{\n" +
                "  \"username\": \"" + userName + "\"\n" +
                "}";

        base.deleteUser(body);

        assertEquals(base.responseStatusCode.getText(), "200", "Status code didn't match !");

        String actualResult = base.registerSuccessMsg.getText().
                substring(1, base.registerSuccessMsg.getText().length() - 1);

        assertEquals(actualResult, "User deleted successfully!");

        assertEquals(base.responseStatusCode.getText(), "200", "Status code didn't match !");
    }



}
