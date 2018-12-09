package pages_for_jenkins;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangeUserPage extends MainPage {

    private String deleteText = "Are you sure about deleting the user from Jenkins?";

    @FindBy (linkText = "someuser")
    private WebElement createdUser;

    @FindBy (xpath = "//a[@href = 'user/someuser/delete']")
    private WebElement deleteUserLink;

    @FindBy (name = "delete")
    private WebElement deleteForm;

    @FindBy (id = "yui-gen1")
    private WebElement yesDeleteButton;

    public ChangeUserPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getYesDeleteButton() {
        return yesDeleteButton;
    }

    public boolean isElementExist(){
        if(createdUser.isDisplayed()) return true;
        return false;
    }

    public void clickDeleteLink(){
        deleteUserLink.click();
    }

    public boolean hasSuchText(){
        clickDeleteLink();
        if(deleteForm.getText().contains(deleteText)) return true;

        return false;
    }

    public boolean hasSuchElOfTableAfterDel () throws NoSuchElementException {

        clickDeleteLink();
        yesDeleteButton.click();

        return createdUser.isDisplayed();


    }

    public boolean hasSuchDeleteLinkInTableAfterDel() throws NoSuchElementException{

        return deleteUserLink.isDisplayed();
    }
}
