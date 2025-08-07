package Pages;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class ServiceCategoryPage {
    @FindBy(id = "name_0")
    WebElement scName;

    @FindBy(xpath = "//*[starts-with(@id, 'radio_field') and contains(@id, '_yes')]")
    WebElement activeTrue;

    @FindBy(className = "o_form_button_save")
    WebElement saveButton;

    @FindBy(className = "o_form_button_cancel")
    WebElement discardButton;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(text(), 'New')]")
    WebElement newButton;

    public ServiceCategoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void dataEntry() throws InterruptedException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode services = mapper.readTree(new File("data/ConfigurationMenu/service_category.json")).get("services");

        for (JsonNode service: services) {
            String name = service.asText();

            newButton.click();
            Thread.sleep(500);
            scName.clear();
            scName.sendKeys(name);
            Thread.sleep(500);
            Assert.assertTrue(activeTrue.isSelected(),"Service Category is not in Active Status!");
            Thread.sleep(500);
            Assert.assertTrue(discardButton.isDisplayed(), "Discard Button is not visible!");
            Thread.sleep(500);
            Assert.assertTrue(saveButton.isDisplayed(), "Save Button is not visible!");
            Thread.sleep(500);
            saveButton.click();
            Thread.sleep(500);
        }
    }
}
