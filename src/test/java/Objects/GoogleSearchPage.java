package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchPage {

    private final WebDriver driver;

    private final By searchbox = By.xpath("//textarea[@id='APjFqb']");
    private final By searchBtn = By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']");
    private final By firstSearchLink = By.xpath("//a//h3[starts-with(text(),'The Economist')]");

    private  final By Banner = By.xpath("//*[@id=\"sp_message_iframe_968152\"]");

    private final By accptAllBTN = By.xpath("//*[@id=\"notice\"]/div[4]/div[2]/button");

    /*
    private final By cookieBanner = By.id("sp_message_iframe_899034");

    private final By trialBanner = By.id("offer_a9e8e59236c797530f33-0");
    private final By okButton = By.xpath("//*[@id='notice']/div[2]/div/div/button[2]");
    private final By minimizeButton = By.xpath("//*[@id=\"template-container\"]/div/div[2]/div/button");

     */
    private final By loginButton = By.xpath("//span[normalize-space()='Log in']");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchGoogle(String searchInput) {
        waitForElementToBeClickable(searchbox).sendKeys(searchInput);
        waitForElementToBeClickable(searchBtn).click();
    }

    public void clickFirstLink() {
        waitForElementToBeClickable(firstSearchLink).click();
    }

    public void banner() {
        switchToFrame(Banner);
        WebElement acceptButton = waitForElementToBeClickable(accptAllBTN);
        acceptButton.click();
    }
/*
    public void clickCookieBtn() {
        switchToFrame(cookieBanner);
        WebElement cookieButton = waitForElementToBeClickable(okButton);
        cookieButton.click();
        switchToDefaultContent();

        switchToFrame(trialBanner);
        WebElement minimizeButtonElement = waitForElementToBeClickable(minimizeButton);
        minimizeButtonElement.click();
        switchToDefaultContent();
    }
*/
    public String loginPageTitle() {
        WebElement loginButtonElement = waitForElementToBeClickable(loginButton);
        loginButtonElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleIs("Login"));
        return driver.getTitle();
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

        private void switchToFrame(By frameLocator) {
            WebElement iframeElement = driver.findElement(frameLocator);
            driver.switchTo().frame(iframeElement);
        }

    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
