package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class VehicleMaintenanceServicePage {
    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(id = "service_category_id_0")
    WebElement serviceCat;

    @FindBy(id = "name_0")
    WebElement serviceDetails;

    @FindBy(xpath = "//*[starts-with(@id,'radio_field') and contains(@id, '_yes')]")
    WebElement activeTrue;

    @FindBy(className = "o_form_button_save")
    WebElement saveButton;

    @FindBy(className = "o_form_button_cancel")
    WebElement discardButton;

    public VehicleMaintenanceServicePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void dataEntry() throws InterruptedException{
        Assert.assertTrue(newButton.isDisplayed(),"New Button is not visible!");
        newButton.click();
        Thread.sleep(500);

        serviceCat.click();
        Thread.sleep(200);
        serviceCat.clear();
        serviceCat.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        serviceDetails.sendKeys("Rotors have a minimum thickness specification. If they are below this, they need to be replaced.");
        Thread.sleep(500);

        Assert.assertTrue(activeTrue.isSelected(), "Vehicle Service Maintenance is not active");
        Thread.sleep(500);

        Assert.assertTrue(discardButton.isDisplayed(), "Discard Button is not visible!");
        Thread.sleep(500);
        Assert.assertTrue(saveButton.isDisplayed(), "Save Button is not visible!");
        Thread.sleep(500);
        saveButton.click();
        Thread.sleep(500);
    }
}
