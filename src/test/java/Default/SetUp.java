package Default;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class SetUp {
    WebDriver driver;

    public WebDriver setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }


}
