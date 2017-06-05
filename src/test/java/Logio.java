import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logio {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private Wait<WebDriver> wait;

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    driver = new ChromeDriver( options );
    baseUrl = "https://www.booking.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 30);
  }

  @Test
  public void LogioTest() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("(//li[@id='current_account']/a/div/span)[2]")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("ulyamajor@mail.ru");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("booking212");
    driver.findElement(By.xpath("//input[@value='Sign in']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='current_account']/a")));
    driver.findElement(By.xpath("//li[@id='current_account']/a")).click();
    driver.findElement(By.cssSelector("input.profile-menu__link.profile-menu__link")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
