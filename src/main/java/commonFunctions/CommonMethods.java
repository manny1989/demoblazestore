package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CommonMethods {
	public static WebDriver driver;
	
	public static WebDriver launchBrowser() {
		DesiredCapabilities capabilities = null;
		String browserType = Util.getConfigData("browser");
		try {
			if (browserType.toUpperCase().equals("CHROME")) {
				System.out.println("chrome");
				capabilities = DesiredCapabilities.chrome();
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities
						.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				driver = new ChromeDriver(capabilities);
			} else if (browserType.toUpperCase().equals("IE")) {
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities
						.internetExplorer();
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(cap);
			} else if (browserType.toUpperCase().equals("FIREFOX")) {
				driver = new FirefoxDriver();
			}
		} catch (Throwable t) {
		}
		return driver;
	}
	
	
	
}
