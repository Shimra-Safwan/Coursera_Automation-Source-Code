package testcases;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashboardPageTest extends BasePage {

    @BeforeClass
    public void loginSignUpPage(){
    }

    @AfterClass
    public void refreshPage() {
        driver.navigate().refresh();
    }

//    @Test(priority = 3)
//    public void verifyLanguageSelection() {
//        dashboardPage.selectLanguage("English");
//    }



}
