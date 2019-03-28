import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SendApplicationTest {
    private static WebDriver driver;

    @Test
    public void canSendApplication() {
        // Setting the web driver location property
        System.setProperty("webdriver.chrome.driver", "src\\test\\webdrivers\\chromedriver.exe");
        //Creating new chrome driver instance
        driver = new ChromeDriver();
        //Maximizing browser window
        driver.manage().window().maximize();
        //Setting implicit wait for elements
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        //Navigating to specific site URL
        driver.get("https://lab08.com/careers/junior-quality-assurance-2/");
        //Initializing accept cookie web element
        WebElement acceptCookieButton = driver.findElement(By.id("euCookieAcceptWP"));
        //Clicking accept cookie button
        acceptCookieButton.click();
        //Initializing password field web element
        WebElement passwordField = driver.findElement(By.id("pwbox-967"));
        scrollDownToElement(passwordField);
        //Clicking inside password field
        passwordField.click();
        //Typing password
        passwordField.sendKeys("selenium-test");
        //Initializing submit button web element
        WebElement submitButton = driver.findElement(By.name("Submit"));
        //Clicking submit button
        submitButton.click();
        //Initialize
        WebElement applyButton = driver.findElement(By.xpath("//a[@class='wp-block-button__link has-text-color has-very-light-gray-color has-background has-luminous-vivid-amber-background-color']"));
        //Scrolling down to element so that it is visible before clicking it
        scrollDownToElement(applyButton);
        //Click
        applyButton.click();
        //Initialize name field
        WebElement nameField = driver.findElement(By.name("your-name"));
        //Initialize email
        WebElement emailField = driver.findElement(By.name("your-email"));
        //Initialize text area for the message
        WebElement messageTextArea = driver.findElement(By.name("your-message"));
        //Initialize send application web element
        WebElement sendApplicationButton = driver.findElement(By.xpath("//input[@value='Send your application form']"));
        nameField.sendKeys("Thomas");
        emailField.sendKeys("tom.ketipov@gmail.com");
        messageTextArea.sendKeys("Selenium Test");
        sendApplicationButton.click();
        //Successful application send text message alert
        WebElement alert = driver.findElement(By.xpath("//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ok']"));
        scrollDownToElement(alert);
        //Asserting that alert text contains the string We appreciate
        Assertions.assertTrue(alert.getText().contains("We appreciate"));

    }

    @AfterAll
    public static void afterAll() {
        //Closing the browser instance
        driver.quit();
    }

    /**
     * Scrolling down to element
     *
     * @param element target element
     */
    private static void scrollDownToElement(WebElement element) {
        System.out.println("Scrolling down");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
