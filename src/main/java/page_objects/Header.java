package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends PageObject{

    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "login")
    private WebElement loginLink;


    public void clickLoginLink() {
        loginLink.click();
    }
}
