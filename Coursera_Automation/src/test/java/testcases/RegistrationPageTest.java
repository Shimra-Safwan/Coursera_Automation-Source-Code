package testcases;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationPageTest extends BasePage {

    @BeforeClass
    public void loginSignUpPage(){
        registrationPage = homePage.clickOnSignUp();
    }

    @BeforeMethod
    public void setup() {
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void verifySignUpPageTitle(){
        Assert.assertTrue(registrationPage.isSignUpDisplayed(), "Sign Up page not displayed");
    }

    @Test(priority = 2)
    public void verifySignUpUser(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        registrationPage.signUpUser("test user"+ timestamp, "test@gmail.com", "#qpe1234#");
        registrationPage.clearFields();
    }

    @Test(priority = 3, dataProvider = "passwordFieldWithInvalidLength")
    public void printStudentDetails(String name, String emailAddress,  String password){
        String timestamp = String.valueOf(System.currentTimeMillis());
        registrationPage.signUpUser(name+ timestamp, emailAddress, password);
        registrationPage.clearFields();
    }

    @DataProvider
    public Object[][] passwordFieldWithInvalidLength(){
        Object[][] userCredentials = new Object[][]{
                {"test use1r","test1@gmail.com", "#qpe1"},
                {"test user2","test2@gmail.com", "P5$uTh8x!jY1qQeW3*Z9#vB4LrT7@wN2^oUjF6!mK0%CpY5nVb&R8xPf3$TzQ1@Jn34"},

        };
        return userCredentials;
    }
}
