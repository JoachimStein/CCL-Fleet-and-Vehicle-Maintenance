package TestRunner;

import Default.HelperMethods;
import Default.LogIn;
import Default.Navigation;
import Default.SetUp;
import Pages.ServiceRequestPage;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class ServiceRequestTest {
    private WebDriver driver;
    private SetUp setUp;
    private LogIn logIn;
    private Navigation nav;
    private ServiceRequestPage srp;

    @BeforeMethod
    public void setUpTest() {
        setUp = new SetUp();
        driver = setUp.setDriver();
        logIn = new LogIn(driver);
        nav = new Navigation(driver);
        srp = new ServiceRequestPage(driver);
    }

//    @Test(priority = 3)
//    public void serviceRequestTest() throws InterruptedException, IOException, InvalidFormatException {
//        logIn.login(driver, "http://192.168.3.187:7071/web/login", "Admin", "1234");
//        nav.navigateTo("Transport Maintenance", "Service Request");
//        srp.dataEntry();
//    }
//
//    @Test(priority = 2)
//    public void serviceRequestWithoutJobCard() throws InterruptedException, IOException, InvalidFormatException {
//        logIn.login(driver, "http://192.168.3.187:7071/web/login", "Admin", "1234");
//        nav.navigateTo("Transport Maintenance", "Service Request");
//        srp.dataEntry();
//        srp.checkOut();
//    }

    @Test(priority = 1)
    public void serviceRequestWithJobCard() throws InterruptedException, IOException, InvalidFormatException {
        logIn.login(driver, "http://192.168.3.187:7071/web/login", "Admin", "1234");
        nav.navigateTo("Transport Maintenance", "Service Request");
        srp.dataEntry();
        srp.setCreateJobCard();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            new HelperMethods().takeScreenShot(driver, result.getName());
        }
        driver.quit();
    }
}
