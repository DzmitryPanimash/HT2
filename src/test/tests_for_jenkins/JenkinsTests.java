package tests_for_jenkins;
import org.openqa.selenium.NoSuchElementException;
import pages_for_jenkins.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;


public class JenkinsTests {
    private String baseUrl = "http://localhost:8080";
    private WebDriver driver = null;
    private StringBuffer buttonsErrors = new StringBuffer();
    private final String BUTTON_COLOR = "#4b758b";
    private ArrayList<String> buttonsColors = new ArrayList<>();


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:/Programm files/chromedriver.exe");  //I know, that "program" has only one 'm'))

        driver = new ChromeDriver();

        driver.get(baseUrl);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        buttonsColors.add(authorizationPage.getSubmitButton().getCssValue("background-color"));
        authorizationPage.authorization("DzmitryPanimash", "ifvbyjg321");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }



    @Test (description = "Find elements dt (with text «Manage Users» and dd (with text «Create/delete/modify users that can login to this Jenkins»")
    public void manageJenkinsTest (){

       DashboardPage dashboardPage = new DashboardPage(driver);
       dashboardPage.clickOnLink();

       ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
       Assert.assertTrue(manageJenkinsPage.findSuchDt(), "[Page Manage]: Element dt didn't found or has a wrong text");
       Assert.assertTrue(manageJenkinsPage.findSuchDd(), "[Page Manage]: Element dd didn't found or has a wrong text");
    }


    @Test (description = "link \"Create User\" will be available after the click on link \"Manage Users\"")
    public void securityRealmTest(){

        driver.get(baseUrl + "/securityRealm/");
        SecurityRealmPage securityRealmPage = new SecurityRealmPage(driver);
        Assert.assertTrue(securityRealmPage.isLinkAvailable(), "[Page SecurityRealm]: Link 'Create User' isn't available!'");
    }

    @Test (description = "Check form with 3 text fields and 2 password fields after the click \"Create User\" link")
    public void addUserFormTest(){

        driver.get(baseUrl + "/securityRealm/");
        SecurityRealmPage securityRealmPage = new SecurityRealmPage(driver);
        securityRealmPage.clickOnLink();
        AddUserPage addUserPage = new AddUserPage(driver);
        buttonsColors.add(addUserPage.getCreateUserButton().getCssValue("background-color"));
        Assert.assertTrue(addUserPage.hasSuchForm(), " [Page AddUser] doesn't have such form!");
        addUserPage.fillArrayByFields();
        Assert.assertEquals(addUserPage.areFieldsEmpty(), "");
        Assert.assertEquals(addUserPage.checkTextTypeOfFields(), "");
        Assert.assertEquals(addUserPage.checkPassTypeOfFields(), "");
    }

    @Test (description = "After creating of new user tge page has a string of the table with text 'someuser'")
    public void creatingUserTest(){

        driver.get(baseUrl + "/securityRealm/");
        SecurityRealmPage securityRealmPage = new SecurityRealmPage(driver);
        securityRealmPage.clickOnLink();
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.createNewUser();
        ChangeUserPage changeUserPage = new ChangeUserPage(driver);
        Assert.assertTrue(changeUserPage.isElementExist(), "[SecurityRealm page]The table doesn't have 'someuser'!");

    }

    @Test(description = "After the click on link 'user/someuser/delete' text appears on the page")
    public void deleteUserTextTest(){
        driver.get(baseUrl + "/securityRealm/");
        ChangeUserPage changeUserPage = new ChangeUserPage(driver);
        Assert.assertTrue(changeUserPage.hasSuchText(), "[Page of deleting of user] No such text on the page!");
        buttonsColors.add(changeUserPage.getYesDeleteButton().getCssValue("background-color"));
    }

    @Test(description = "After the click on Yes-button (delete user) tge page doesn't have a string of the table with text 'someuser' and link of deleting ", expectedExceptions = NoSuchElementException.class)
    public void isDeleteUserSuccess (){
        driver.get(baseUrl + "/securityRealm/");
        ChangeUserPage changeUserPage = new ChangeUserPage(driver);
        changeUserPage.hasSuchElOfTableAfterDel(); //"[SecurityRealm page]The table still has 'someuser' (after deleting)!"
        changeUserPage.hasSuchDeleteLinkInTableAfterDel(); //"[SecurityRealm page]The table still has 'user/someuser/delete' link (after deleting)!"
    }

    @Test (description = "The link 'user/admin/delete' doesn't exist on the SecurityRealm page", expectedExceptions = NoSuchElementException.class)
    public void isAdminDeleteExistTest(){
        driver.get(baseUrl + "/securityRealm/");
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.isAdminDeleteLinkExist();
    }

    @Test(description = "test of color of buttons")
    public void colorOfButtonsTest (){
        for(int i = 0; i < buttonsColors.size(); i++) {
           if(!Color.fromString(buttonsColors.get(i)).asHex().equalsIgnoreCase(BUTTON_COLOR)) {
               buttonsErrors.append("Button " + (i+1) + " doesn't have such color! ");
           }
        }
        String errors = buttonsErrors.toString();
        if (!"".equals(errors)) {
            Assert.fail(errors);
        }

    }
}
