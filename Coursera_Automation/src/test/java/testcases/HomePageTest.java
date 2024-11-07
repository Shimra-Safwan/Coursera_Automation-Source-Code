package testcases;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BasePage {

    @AfterClass
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void signInButtonTest() {
        boolean flag = homePage.isSignInButtonDisplayed();
        Assert.assertTrue(flag);
    }

    @Test(priority = 2)
    public void verifyClickOnSignUpLink() {
        registrationPage = homePage.clickOnSignUp();
    }


    @Test(priority = 4)
    public void verifySearchCourse(){
        homePage.TypeTextToSearchBox("Introduction to Software Testing");
    }

//    @Test(priority = 3)
//    public void verifyClickOnSignInLink() {
//        loginPage = homePage.clickOnSignIn();
//    }
}
