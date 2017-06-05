import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Form {
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
  public void testForm() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys("baku");
    driver.findElement(By.xpath("//form[@id='frm']/div[2]/div/div/ul/li")).click();
    driver.findElement(By.xpath("//form[@id='frm']/div[3]/div/div/div/div/div/div[2]/div[2]/div[3]/div/div/div/table/tbody/tr[5]/td[5]/span")).click();
    driver.findElement(By.xpath("//form[@id='frm']/div[3]/div/div/div[2]/div/div/div/div/i")).click();
    driver.findElement(By.xpath("//form[@id='frm']/div[3]/div/div/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/table/tbody/tr[3]/td/span")).click();
    driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[2]")).click();
    driver.findElement(By.id("group_adults")).click();
    new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText("1");
    driver.findElement(By.cssSelector("#group_adults > option[value=\"1\"]")).click();
    driver.findElement(By.cssSelector("button.sb-searchbox__button.")).click();
    driver.findElement(By.id("b2searchresultsPage")).click();
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
