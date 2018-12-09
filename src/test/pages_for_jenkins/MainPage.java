package pages_for_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

public MainPage(WebDriver driver){
    this.driver = driver;
    this.wait = new WebDriverWait(this.driver, 15);

    }

}
