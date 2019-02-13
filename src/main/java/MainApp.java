import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.IndexPage;
import page_objects.SignInPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainApp {

    private WebDriver driver;
    private WebDriverWait wait;
    private IndexPage indexPage;
    private SignInPage signInPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);

        indexPage = new IndexPage(driver);
        signInPage = new SignInPage(driver);

    }

    @AfterClass
    public void tearDown() {
        sleep(2000);
        driver.quit();
    }

    @Test
    public void loginErrorTest(){
        driver.get("http://automationpractice.com/index.php");
        indexPage.clickLoginLink();
        signInPage.clickSignInLink();
        assertThat(signInPage.isAlertOn());
    }

















    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}