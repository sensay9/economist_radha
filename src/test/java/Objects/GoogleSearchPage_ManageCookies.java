package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GoogleSearchPage_ManageCookies {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieBanner = By.id("sp_message_iframe_899034");

    private final By manageButton = By.xpath("//*[@id='notice']/div[2]/div/div/button[1]");

    private final By manageCookieBanner = By.id("sp_message_iframe_744617");

    private final By privacyPolicyLink = By.xpath("//a[text()='Privacy Policy']");

    private final By doNotSellBtn = By.xpath("//div[@title='Do Not Sell or Share']//span[@class='slider round']");

    private final By saveAndCloseBtn = By.xpath("//button[normalize-space()='Save and Close']");

    private String mainWindowHandle;


    public GoogleSearchPage_ManageCookies(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickManageCookieBtn() {

        mainWindowHandle = driver.getWindowHandle();
        System.out.println(mainWindowHandle);

        WebElement iframeElement = driver.findElement(cookieBanner);
        driver.switchTo().frame(iframeElement);

        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(manageButton));
        cookieButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeElement2 = driver.findElement(manageCookieBanner);
        driver.switchTo().frame(iframeElement2);
        WebElement doNotSell_Button = wait.until(ExpectedConditions.elementToBeClickable(doNotSellBtn));
        doNotSell_Button.click();

    }

    public String publicPolicyLinkChecking() {

        WebElement privacyPolicyLinkCheck = wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyLink));
        privacyPolicyLinkCheck.click();

        Set<String> allWindowHandles = driver.getWindowHandles();
        if (allWindowHandles.size() >= 2) {

            List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
            String secondWindowHandle = windowHandlesList.get(1);
            driver.switchTo().window(secondWindowHandle);
        }

        return driver.getCurrentUrl();

    }

    public void saveAndClose() {
        driver.switchTo().window(mainWindowHandle);
       // driver.switchTo().window(mainWindowHandle);
        WebElement iframeElement2 = driver.findElement(manageCookieBanner);
        driver.switchTo().frame(iframeElement2);
        WebElement saveClose_Button = wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseBtn));
        saveClose_Button.click();
    }


}
