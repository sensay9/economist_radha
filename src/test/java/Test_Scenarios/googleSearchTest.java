package Test_Scenarios;

import Objects.GoogleSearchPage;
import Objects.GoogleSearchPage_ManageCookies;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class googleSearchTest {

    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;
    private GoogleSearchPage_ManageCookies googleSearchPage_ManageCookies;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.google.com/");
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage_ManageCookies = new GoogleSearchPage_ManageCookies(driver);
    }

    @Test(priority = 1)
    public void searchOperation() {
        googleSearchPage.searchGoogle("economist");
    }

    @Test(priority = 2)
    public void clickFirstSearchLink() {
        googleSearchPage.clickFirstLink();
    }

    @Test(priority = 3 )
    public void navigateToSearchedWebsite() {
        googleSearchPage.banner();
    }

        @Test(priority = 4)
        public void navigateToLoginPage() {
            Assert.assertEquals(googleSearchPage.loginPageTitle(), "Login", "Page title is not 'Login'");
        }
/*
            @Test(priority = 5 )
            public void manageCookies() {
                googleSearchPage_ManageCookies.clickManageCookieBtn();
            }

            @Test(priority = 6 )
            public void policyLinkUrlValidation() {
                String expectedUrl = "https://www.economistgroup.com/privacy-policy";
                String actualUrl = googleSearchPage_ManageCookies.publicPolicyLinkChecking();
                Assert.assertEquals(actualUrl, expectedUrl, "Actual URL does not match the expected URL");
            }

            @Test(priority = 7 )
            public void saveAndClose() {
                googleSearchPage_ManageCookies.saveAndClose();
            }
*/
    @AfterTest
    public void afterTest() {
        driver.quit();
    }


}
