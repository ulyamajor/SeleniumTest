/**
 * Created by Yifei Wu on 6/4/2017.
 */
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class UploadFile {
    @Test
    public void uploadFileTest() {
        WebDriver driver;
        Wait<WebDriver> wait;
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver( options );
        wait = new WebDriverWait(driver, 30);
        driver.get( "http://demo.guru99.com/selenium/upload/");
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
        uploadElement.sendKeys("c:/chromedriver.exe");
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.name("send")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("res")));
        assertEquals("1 file\nhas been successfully uploaded.",driver.findElement(By.id("res")).getText());
        driver.close();

    }
}