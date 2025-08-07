package Default;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LogIn {
    @FindBy(id = "login")
    WebElement userNameInput;

    // Password
    @FindBy(id = "password")
    WebElement userPasswordInput;

    // Login Button
    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement loginButton;

    public LogIn(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void login(WebDriver driver, String url, String userName, String passWord) throws InterruptedException{
        driver.get(url);
        Thread.sleep(1000);
        userNameInput.sendKeys(userName);
        Thread.sleep(500);
        userPasswordInput.sendKeys(passWord);
        Thread.sleep(500);
        loginButton.click();
        Thread.sleep(500);

        Assert.assertTrue(driver.getTitle().startsWith("CCPLC"), "Login Failed!");

    }
}
