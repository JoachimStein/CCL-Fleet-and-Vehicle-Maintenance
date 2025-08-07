package TestRunner;

import Default.LogIn;
import Default.Navigation;
import Default.SetUp;
import Pages.ServiceCategoryPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ServiceCategoryTest {
    private WebDriver driver;
    private SetUp setUp;
    private LogIn login;
    private Navigation nav;
    private ServiceCategoryPage serviceCat;

    String url = "http://192.168.3.187:7071/web/login";
    String userName = "Admin";
    String passWord = "1234";

    @BeforeTest
    public void setUpTest(){
        setUp = new SetUp();
        driver = setUp.setDriver();
        login = new LogIn(driver);
        nav = new Navigation(driver);
        serviceCat = new ServiceCategoryPage(driver);
    }

    @Test(priority = 1)
    public void logInTest() throws InterruptedException{
        login.login(driver, url, userName, passWord);
        Thread.sleep(500);
    }

    @Test(priority = 2)
    public void navigationTest() throws InterruptedException{
        login.login(driver, url, userName, passWord);
        nav.navigateTo("Configuration","Service Category");
        Thread.sleep(500);
    }

    @Test(priority = 3)
    public void newServiceCategoryCreationTest() throws InterruptedException, IOException {
        login.login(driver, url, userName, passWord);
        nav.navigateTo("Configuration","Service Category");
        serviceCat.dataEntry();
    }

    @AfterTest
    public void end(){
        driver.close();
        driver.quit();
    }
}
