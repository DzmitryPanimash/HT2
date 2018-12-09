package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageJenkinsPage extends MainPage {

    @FindBy(xpath = "//a[@title = 'Manage Users']/dl/dt")
    private WebElement dt;

    @FindBy(xpath = "//a[@title = 'Manage Users']/dl/dd")
    private WebElement dd;

    @FindBy (xpath = "//a[@title = 'Manage Users']")
    private WebElement manageUsersLink;

    public ManageJenkinsPage (WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ManageJenkinsPage clickOnLink(){
        manageUsersLink.click();
        return this;
    }

    public boolean findSuchDt (){

        if(this.dt.isDisplayed())
        {
            if(this.dt.getText().equals("Manage Users"))
            {
                return true;
            }
        }

        return false;
    }

    public boolean findSuchDd (){

        if(this.dd.isDisplayed())
        {
            if(this.dd.getText().equals("Create/delete/modify users that can log in to this Jenkins"))
            {
                return true;
            }
        }

        return false;

    }
}
