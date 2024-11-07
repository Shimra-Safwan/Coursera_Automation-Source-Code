package testcases;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.TestUtil;

import java.util.concurrent.TimeUnit;


public class LoginPageTest extends BasePage {

   @BeforeClass
   public void loginSignInPage(){
      loginPage = homePage.clickOnSignIn();
   }

   @AfterMethod
   public void setup() {
      driver.navigate().refresh();

   }

   @Test(priority = 1)
   public void verifySignInPageTitle(){
   Assert.assertTrue(loginPage.isSignInDisplayed(), "Log In page not displayed");
   }

   @Test(priority = 2)
   public void verifyClickOnSignUpLink() {
      registrationPage = loginPage.clickOnSignUpLink();
   }

   @Test(priority = 3)
   public void verifyUserWithValidUsernameAndValidPassword() {
      dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
//      Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(), "Dashboard page not displayed");
      if (dashboardPage != null && dashboardPage.isAccountIconDisplayed()) {
         driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
         dashboardPage.clickOnAccountIcon();
      }
   }

   @Test(priority = 4)
   public void verifyUserWithInvalidUsernameAndInvalidPassword() {
      loginPage = homePage.clickOnSignIn();
      String incorrectUsername = "testUser1";
      String incorrectPassword = "12345";
      dashboardPage = loginPage.login(incorrectUsername, incorrectPassword);
      String errorMessage = loginPage.getErrorMessage();
      Assert.assertFalse(errorMessage.contains("Sorry, we don't recognize that username or password. You can try again or reset your password"));
      loginPage.clearFields();
   }

   @Test(priority = 5)
   public void verifyUserWithValidUsernameAndInvalidPassword() {
      String incorrectPassword = "dev";
      dashboardPage = loginPage.login(prop.getProperty("username"), incorrectPassword);
      String errorMessage = loginPage.getErrorMessage();
      Assert.assertTrue(errorMessage.contains("Sorry, we don't recognize that username or password. You can try again or reset your password"));
      loginPage.clearFields();
   }

   @Test(priority = 6)
   public void verifyUserWithInvalidUsernameAndValidPassword() {
      String incorrectUsername = "testUser1";
      dashboardPage = loginPage.login(incorrectUsername, prop.getProperty("password"));
      String errorMessage = loginPage.getErrorMessage();
      Assert.assertFalse(errorMessage.contains("Sorry, we don't recognize that username or password. You can try again or reset your password"));
      loginPage.clearFields();
   }

   @Test(priority = 7)
   public void verifyUserWithEmptyUsernameAndPassword() {
      dashboardPage = loginPage.login("", "");
      Assert.assertTrue(loginPage.loginBtn.isEnabled());
   }

   @Test(priority = 8)
   public void verifyUserWithValidUsernameAndEmptyPassword() {
      dashboardPage = loginPage.login(prop.getProperty("username"), "");
      loginPage.username.clear();
   }

   @Test(priority = 9)
   public void verifyUserWithEmptyUsernameAndValidPassword() {
      dashboardPage = loginPage.login("", prop.getProperty("password"));
      loginPage.password.clear();
   }

//   @Test(priority = 10)
//   public void verifyTheUserRedirectToForgottenPasswordPage() {
//      forgottenPasswordPage = loginPage.clickForgotPasswordLink();
////      Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordDisplayed(), "Forgot Password page not displayed");
//      driver.navigate().back();
//   }


}