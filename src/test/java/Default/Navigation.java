package Default;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Navigation {
    private WebDriver driver;

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String menuName, String subMenuName) throws InterruptedException {
        driver.findElement(By.cssSelector("[title=\"Home Menu\"]")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("Fleet")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[text()=\"" + menuName + "\"]")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText(subMenuName)).click();
        Thread.sleep(500);

        Assert.assertTrue(driver.getTitle().contains(subMenuName),"Navigation Failed! " + subMenuName + " not found!");
    }
}
