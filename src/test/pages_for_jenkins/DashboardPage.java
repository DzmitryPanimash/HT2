package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends MainPage {

    @FindBy (partialLinkText = "Manage Jenkins")
    private WebElement manageJenkinsLink;

    public DashboardPage (WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public DashboardPage clickOnLink (){
        manageJenkinsLink.click();
        return this;
    }
}
