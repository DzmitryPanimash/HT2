package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AuthorizationPage extends MainPage {

    private String username = "Username";
    private String password = "Password";

    @FindBy (name = "j_username")
    private WebElement usernameField;

    @FindBy (name = "j_password")
    private WebElement passwordField;

    @FindBy (name = "Submit")
    private WebElement submitButton;


    public AuthorizationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSubmitButton() {
        return submitButton;

    }

    public AuthorizationPage authorization (String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.submit();
        return this;
    }
}
