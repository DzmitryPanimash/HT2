package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AddUserPage extends MainPage {

    ArrayList<WebElement> formFields = new ArrayList<>();
    private String textTypeOfFields = "";
    private String passTypeOfFields = "";
    private StringBuffer areEmpty = new StringBuffer();
    private String newUsername = "someuser";
    private String newPassword = "somepassword";
    private String newFullName = "Some Full Name";
    private String newEmail = "some@addr.dom";


    @FindBy (xpath = "//div[@id='main-panel']/form[1]")
    private WebElement formForNewUser;

    @FindBy (id = "username")
    private WebElement usernameField;

    @FindBy (xpath = "//input[@name = 'password1']")
    private WebElement passwordField;

    @FindBy (xpath = "//input[@name = 'password2']")
    private WebElement confirmPasswordField;

    @FindBy (xpath = "//input[@name = 'fullname']")
    private WebElement fullnameField;

    @FindBy (xpath = "//input[@name = 'email']")
    private WebElement emailField;

    @FindBy (id = "yui-gen1")
    private WebElement createUserButton;

    @FindBy(xpath = "//a[@href = 'user/admin/delete']")
    private WebElement adminDeleteLink;


    public AddUserPage (WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getCreateUserButton() {
        return createUserButton;
    }

    public void fillArrayByFields(){

        this.formFields.add(usernameField);
        this.formFields.add(passwordField);
        this.formFields.add(confirmPasswordField);
        this.formFields.add(fullnameField);
        this.formFields.add(emailField);

    }

    public boolean hasSuchForm(){

        if(formForNewUser.isDisplayed()) return true;
        return false;
    }

    public String areFieldsEmpty(){
        int counter = 0;
        for (WebElement e : this.formFields)
        {
            counter++;
            if(e.getText().equals("")) {}
            else {
                this.areEmpty.append(counter).append(", ");
            }

        }

        if(areEmpty.length() != 0){
            this.areEmpty.append(" fields aren't empty on [AddUser page]!");
        }

        return areEmpty.toString();
    }

    public String checkTextTypeOfFields(){

        int textFields = 0;

        for (WebElement e : this.formFields){
            if(e.getAttribute("type").equals("text"))
            {
                textFields++;
            }

        }

        if(textFields == 3) {
            return textTypeOfFields;
        } else {
            textTypeOfFields = "There is (are) " + textFields + " text field(s) in the form on [AddUser page]";
        }
        return textTypeOfFields;
    }


    public String checkPassTypeOfFields(){

        int passwordFields = 0;

        for (WebElement e : this.formFields){

            if(e.getAttribute("type").equals("password"))
            {
                passwordFields++;
            }
        }

        if(passwordFields == 2) {
            return passTypeOfFields;
        } else {

            passTypeOfFields = "There is (are) " + passwordFields + " password field(s) in the form on [AddUser page]";
        }

        return passTypeOfFields;
    }

    public AddUserPage createNewUser (){

       usernameField.sendKeys(newUsername);
       passwordField.sendKeys(newPassword);
       confirmPasswordField.sendKeys(newPassword);
       fullnameField.sendKeys(newFullName);
       emailField.sendKeys(newEmail);

        createUserButton.click();

        return this;
    }

    public boolean isAdminDeleteLinkExist() throws NoSuchElementException {

        return adminDeleteLink.isDisplayed();
    }
}
