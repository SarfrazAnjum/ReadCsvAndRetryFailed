import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SwagLabsDataDriven {
	WebDriver driver;

	@BeforeSuite
	public void driverInit() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProvider = "getData")
	public void swagLabLogTest(String username, String password) {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();

	}

	@DataProvider(name = "getData")
	public Object[][] getData() throws IOException, CsvException {
		CSVReader reader = new CSVReaderBuilder(new FileReader("data/users.csv")).withSkipLines(1).build();
		List<String[]> records = reader.readAll();
		Object[][] array = null;
		for (int i = 0; i < records.size(); i++) {
			Object[] row = records.get(i);
			if (Objects.isNull(array)) {
				array = new Object[records.size()][row.length];
			}
			array[i][0] = row[0];
			array[i][1] = row[1];

		}
		return array;

	}
}
