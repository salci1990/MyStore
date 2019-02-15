import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MainApp {

    private WebDriver driver;
    private WebDriverWait wait;
    private IndexPage indexPage;
    private SignInPage signInPage;
    private Header header;
    private LayerCard layerCard;
    private ShoppingCard shoppingCard;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 60);

        indexPage = new IndexPage(driver);
        signInPage = new SignInPage(driver);
        header = new Header(driver);
        layerCard = new LayerCard(driver);
        shoppingCard = new ShoppingCard(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

//    @Test
//    public void loginErrorTest() {
//        driver.get("http://automationpractice.com/index.php");
//        header.clickLoginLink();
//        signInPage.clickSignInLink();
//        assertThat(signInPage.isAlertOn());
//    }

    @Test
    public void TotalPriceTest(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        Actions builder = new Actions(driver);
        builder.moveToElement(indexPage.findElement().get(2)).build().perform();

        indexPage.clickAddButton();

        wait.until(ExpectedConditions.visibilityOf(layerCard.exitWindow()));
        assertThat(layerCard.exitWindow().isDisplayed()).isTrue();
        layerCard.exitWindowSuccess();

        builder.moveToElement(indexPage.findElement().get(3)).build().perform();

        indexPage.clickAddButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOf(layerCard.exitWindow()));
        assertThat(layerCard.exitWindow().isDisplayed()).isTrue();
        layerCard.exitWindowSuccess();

        builder.moveToElement(header.moveToShopingCard()).build().perform();
        wait.until(ExpectedConditions.visibilityOf(header.checkPriceCard()));
        assertThat(header.checkPriceCard().isDisplayed()).isTrue();
        header.openShoppingCard();

        String total = String.format("%8.2f",shoppingCard.getTotalPrice());
        String sum = String.format("%8.2f", shoppingCard.getFirstPrice() + shoppingCard.getSecondPrice() + shoppingCard.getShipping());
        assertThat(sum).isEqualTo(total);
    }
}
