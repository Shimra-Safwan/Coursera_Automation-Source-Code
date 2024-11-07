package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    //object repositories
    @FindBy(xpath = "//a[@data-e2e='header-signup-button']")
    public WebElement signInButton;

    @FindBy(xpath = "//a[@data-track-component='header_right_nav_button' and @data-track-page='front_page_story' and @data-track-app='front_page']")
    public WebElement logInButton;

    @FindBy(xpath = "//input[@id='search-autocomplete-input']")
    public WebElement searchBar;

    //initializing the page objects using HomePage constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    //actions( functionalities of HomePage)

    // Method to check if the signUp button is displayed
    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

    //Method to redirect to LogIn page
    public LoginPage clickOnSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
        return new LoginPage(driver);
    }

    public void TypeTextToSearchBox(String searchText){
        searchBar.sendKeys(searchText);
    }

    // Method to click on the signUp button
    public RegistrationPage clickOnSignUp() {
        signInButton.click();
        return new RegistrationPage(driver);
    }
}
