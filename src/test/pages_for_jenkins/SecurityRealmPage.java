package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurityRealmPage extends MainPage {

    @FindBy (linkText = "Create User")
    private WebElement createUserLink;

    public SecurityRealmPage (WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SecurityRealmPage clickOnLink(){
        createUserLink.click();
        return this;
    }

    public boolean isLinkAvailable(){

        if(this.createUserLink.isDisplayed()) return true;

        return false;
    }
}
