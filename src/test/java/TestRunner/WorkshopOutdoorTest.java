package TestRunner;

import Default.LogIn;
import Default.Navigation;
import Default.SetUp;
import Pages.WorkshopOutdoorPage;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class WorkshopOutdoorTest {
    private WebDriver driver;
    private SetUp setUp;
    private LogIn logIn;
    private Navigation nav;
    private WorkshopOutdoorPage wop;

    @BeforeTest
    public void setUpTest() {
        setUp = new SetUp();
        driver = setUp.setDriver();
        logIn = new LogIn(driver);
        nav = new Navigation(driver);
        wop = new WorkshopOutdoorPage(driver);
    }

    @Test
    public void workshopOutdoorTest() throws InterruptedException, IOException, InvalidFormatException {
        logIn.login(driver, "http://192.168.3.187:7071/web/login", "Admin", "1234");
        nav.navigateTo("Configuration", "Workshop Outdoor");
        wop.excelSetUp();
        wop.dataEntry();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
