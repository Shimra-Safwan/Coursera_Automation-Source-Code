package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import utils.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver;
    public static Properties prop;
    public static LoginPage loginPage;
    public static ForgottenPasswordPage forgottenPasswordPage;
    public static HomePage homePage;
    public static RegistrationPage registrationPage;
    public static DashboardPage dashboardPage;


    //constructor to reading the properties
    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not found at the specific path");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading config.properties file");
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        initialization();
        loginPage = new LoginPage(driver); // creating the LoginPage class object to access all the methods of LoginPage class
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void initialization() {
        String browserName = prop.getProperty("browser"); //reading the properties
        Boolean headlessMode = Boolean.parseBoolean(prop.getProperty("headless")); //setting the headless mode

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
//            options.setHeadless(headlessMode);
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
//            options.setHeadless(headlessMode);
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

}