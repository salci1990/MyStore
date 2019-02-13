package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends PageObject {

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Printed Dress")
    private WebElement firstElementToAdd;

    @FindBy(linkText = "Blouse")
    private WebElement secondElementToAdd;

    public WebElement firstElement() {
        return firstElementToAdd;
    }

    public WebElement secondElement() {
        return secondElementToAdd;
    }
}
