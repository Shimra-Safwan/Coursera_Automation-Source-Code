package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    //object repositories
    @FindBy(xpath = "//h1[@class='_297whj']")
    WebElement logInHeader;

    @FindBy(name = "email")
    public WebElement username;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@data-e2e='login-form-submit-button']")
    public WebElement loginBtn;

    @FindBy(xpath = "Forgot password?")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//div[@role='alert' and @aria-live='assertive']")
    WebElement errorMessage;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpLink;

    //initializing the page objects using LoginPage constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    //actions( functionalities of LoginPage)

    public boolean isSignInDisplayed(){
        return logInHeader.isDisplayed();
    }

    //function to navigate to registration page
    public RegistrationPage clickOnSignUpLink() {
        signUpLink.click();
        return new RegistrationPage(driver);
    }

    //function to Login with username and password
    public DashboardPage login(String un, String pwd) {
        username.sendKeys(un);
        password.sendKeys(pwd);
        loginBtn.click();
        return new DashboardPage(driver);
    }

//    //function to forgot password link clicking
    public ForgottenPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ForgottenPasswordPage(driver);
    }

    //function to getting the error message
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    //function to clear the input fields
    public void clearFields() {
        username.clear();
        password.clear();
    }
}