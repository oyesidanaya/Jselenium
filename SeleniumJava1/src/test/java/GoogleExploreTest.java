import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleExploreTest {

	private WebDriver driver;
	
	@Before
	public void setUp() {
		String osName = System.getProperty("os.name");
		System.out.println("Init webdriver in OS: " + osName);
		if(osName.startsWith("Mac")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver_mac");
		} else {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver_win.exe");
		}
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@Test
	public void exploreTest() {
		driver.navigate().to("https://www.google.com/");
		WebElement searchInput = driver.findElement(By.name("q"));
		searchInput.clear();
		searchInput.sendKeys("seleniumHq");
		searchInput.submit();
		
		WebElement resultElement = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div.r > a:nth-child(1) > h3"));
		
		assertEquals("Selenium - Web Browser Automation", resultElement.getText());
	}
}
