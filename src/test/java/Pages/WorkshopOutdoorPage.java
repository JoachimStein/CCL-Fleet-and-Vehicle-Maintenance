package Pages;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WorkshopOutdoorPage {
    FileInputStream fis;
    Workbook workbook;
    Sheet sheet;
    int rowCount;

    @FindBy(id = "name_0")
    WebElement workshop;

    @FindBy(id = "address_0")
    WebElement address;

    @FindBy(id = "machine_0")
    WebElement mechanic;

    @FindBy(id = "mobile_no_0")
    WebElement mobile;

    @FindBy(xpath = "//*[starts-with(@id, 'radio_field') and contains(@id, '_yes')]")
    WebElement activeTrue;

    @FindBy(className = "o_form_button_save")
    WebElement saveButton;

    @FindBy(className = "o_form_button_cancel")
    WebElement discardButton;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(text(), 'New')]")
    WebElement newButton;

    public WorkshopOutdoorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void excelSetUp() throws IOException, InvalidFormatException {
        File file = new File("data/ConfigurationMenu/workshop_outdoor_testData.xlsx");
        fis = new FileInputStream(file);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheetAt(0); // First Sheet
        rowCount = sheet.getPhysicalNumberOfRows(); // Total Number of Rows
    }

    public void dataEntry() throws InterruptedException {
        for (int i=1; i < rowCount; i++) { // Start from 1 to skip the header
            Row row = sheet.getRow(i);
            String workshopName = row.getCell(0).getStringCellValue();
            String addressName = row.getCell(1).getStringCellValue();
            String mechanicName = row.getCell(2).getStringCellValue();
            double mobileNo = row.getCell(3).getNumericCellValue();

            assert newButton.isDisplayed();
            newButton.click();
            Thread.sleep(500);

            workshop.sendKeys(workshopName);
            Thread.sleep(500);
            address.sendKeys(addressName);
            Thread.sleep(500);
            mechanic.sendKeys(mechanicName);
            Thread.sleep(500);
            mobile.sendKeys(String.valueOf(mobileNo));
            Thread.sleep(500);

            assert activeTrue.isSelected();
            assert discardButton.isDisplayed();
            assert saveButton.isDisplayed();
            saveButton.click();
            Thread.sleep(500);
        }
    }
}
