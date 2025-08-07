package Pages;

import Default.HelperMethods;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class ServiceRequestPage {
    private HelperMethods helperMethods;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(name = "name")
    WebElement serviceRequestNo;

    @FindBy(name = "service_request_id")
    WebElement serviceRequestId;

    @FindBy(id = "vehicle_id_0")
    WebElement vehicleId;

    @FindBy(name = "date")
    WebElement date;

    @FindBy(name = "chassis_no")
    WebElement chassisNo;

    @FindBy(name = "engine_no")
    WebElement engine_no;

    @FindBy(name = "driver_name")
    WebElement driverName;

    @FindBy(id = "helper_name_0")
    WebElement helperName;

    @FindBy(name = "company_id")
    WebElement companyId;

    @FindBy(name = "vehicle_mood")
    WebElement vehicleMode;

    @FindBy(id = "problem_details_0")
    WebElement problem_details;

    @FindBy(id = "supervisor_id_0")
    WebElement supervisorName;

    @FindBy(id = "engineer_id_0")
    WebElement engineerName;

    @FindBy(id = "remarks_no_0")
    WebElement masterRemarks;

    @FindBy(linkText = "Add a line")
    WebElement addNewLine;

    @FindBy(name = "service_category_id")
    WebElement serviceCategoryId;

    @FindBy(name = "service_id")
    WebElement serviceDetails;

    @FindBy(name = "mechanic_id")
    WebElement mechanicName;

    @FindBy(name = "job_details")
    WebElement jobDetails;

    @FindBy(name = "check_in_date")
    WebElement checkInDate;

    @FindBy(className = "o_form_button_save")
    WebElement saveButton;

    @FindBy(className = "o_form_button_cancel")
    WebElement discardButton;

    @FindBy(name = "action_to_maintenance")
    WebElement submitButton;

    @FindBy(name = "action_to_checkout")
    WebElement checkOutButton;

    @FindBy(name = "action_create_job_card")
    WebElement createJobCardButton;

    @FindBy(xpath = "//*[starts-with(@id, 'radio_field') and contains(@id, '_yes')]")
    WebElement isOutdoorWorkshopServiceRequired;

    @FindBy(xpath = "//*[starts-with(@id, 'radio_field') and contains(@id, '_no')]")
    WebElement isNotOutdoorWorkshopServiceRequired;

    @FindBy(css = "[special=\"cancel\"]")
    WebElement cancelButton;

    @FindBy(name = "action_confirm")
    WebElement confirmButton;

    public ServiceRequestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void dataEntry() throws InterruptedException, IOException, InvalidFormatException {
        Random random = new Random();
        FileInputStream fis = new FileInputStream(new File("data/ServiceRequest(Transport)/Service_Request_testData.xlsx"));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet1 = workbook.getSheetAt(0);
        Sheet sheet2 = workbook.getSheetAt(1);
        int totalRowsS1 = sheet1.getPhysicalNumberOfRows();
        int totalRowsS2 = sheet2.getPhysicalNumberOfRows();
        int randomRowIndexS1 = random.nextInt(totalRowsS1-1)+1;
        int randomRowIndexS2 = random.nextInt(totalRowsS2-1)+1;
        Row row1 = sheet1.getRow(randomRowIndexS1);
        Row row2 = sheet2.getRow(randomRowIndexS2);

        String licensePlate = row1.getCell(0).getStringCellValue();
        String chassisNumber = row1.getCell(1).getStringCellValue();
        String driverInfo = row1.getCell(2).getStringCellValue();
        String engineNo = row1.getCell(3).getStringCellValue();
        String helperInfo = row2.getCell(0).getStringCellValue();
        String problemDetails = row2.getCell(1).getStringCellValue();

        assert newButton.isDisplayed();
        newButton.click();
        Thread.sleep(500);

        vehicleId.click();
        Thread.sleep(500);
        vehicleId.sendKeys(licensePlate);
        Thread.sleep(1000);
        vehicleId.sendKeys(Keys.ENTER);

        helperName.click();
        Thread.sleep(500);
        helperName.sendKeys(helperInfo);
        Thread.sleep(1000);
        helperName.sendKeys(Keys.ENTER);

        problem_details.click();
        problem_details.sendKeys(problemDetails);
        Thread.sleep(500);

        helperMethods = new HelperMethods();
        String currentDate = helperMethods.currentDate();
        Assert.assertEquals(currentDate, date.getText(), "Date is not set to current date!");
        Thread.sleep(1000);

        Assert.assertEquals(chassisNo.getText(), chassisNumber, "Actual Data: " + chassisNumber + "\nSystem Generated: " + chassisNo.getText());
        Thread.sleep(1000);
        Assert.assertEquals(companyId.getText(), "CONFIDENCE CEMENT PLC", "Company Name did not match!");
        Thread.sleep(1000);
        Assert.assertEquals(engine_no.getText(), engineNo, "Engine No. mismatched!");
        Thread.sleep(1000);

        assert discardButton.isDisplayed();
        assert saveButton.isDisplayed();
        saveButton.click();
        Thread.sleep(500);

        Assert.assertEquals(vehicleMode.getText(), "Under Servicing", "Vehicle mode has not changed!");
        Thread.sleep(500);

        Assert.assertEquals(submitButton.getText(), "Submit", "Submit button string incorrect!");
        submitButton.click();
        Thread.sleep(500);

        Assert.assertEquals(driverName.getText(), driverInfo, "Driver names mismatched!");
        Thread.sleep(1000);
        Assert.assertEquals(checkOutButton.getText(), "Check Out", "Check out button is mismatched!");
        Thread.sleep(500);
        Assert.assertEquals(createJobCardButton.getText(), "Create Job Card", "Create Job Card button string is mismatched!");
        Thread.sleep(500);
    }

    public void checkOut() throws InterruptedException {
        checkOutButton.click();
        Thread.sleep(500);
        Assert.assertEquals(serviceRequestNo.getText(), serviceRequestId.getText(), "Service Request No. does not match!");
        assert confirmButton.isDisplayed();
        assert cancelButton.isDisplayed();
        confirmButton.click();
        Thread.sleep(500);

        Assert.assertEquals(vehicleMode.getText(), "Available", "Vehicle mode is not set to available after maintenance!");
        Thread.sleep(1000);
    }

    public void setCreateJobCard() throws InterruptedException {
        createJobCardButton.click();
        Thread.sleep(1000);

        Assert.assertTrue(isNotOutdoorWorkshopServiceRequired.isSelected(), "Outdoor Worksop Service should not be required to yes by default!");
        Thread.sleep(500);

        supervisorName.click();
        Thread.sleep(500);
        supervisorName.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        engineerName.click();
        Thread.sleep(500);
        engineerName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        masterRemarks.sendKeys("Automation Testing");
        Thread.sleep(500);

        assert addNewLine.isDisplayed();
        addNewLine.click();
        Thread.sleep(500);

        serviceCategoryId.click();
        Thread.sleep(500);
        serviceCategoryId
                .findElement(By.tagName("input"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        serviceDetails.click();
        Thread.sleep(500);
        serviceDetails
                .findElement(By.tagName("input"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        mechanicName.click();
        Thread.sleep(500);
        mechanicName
                .findElement(By.tagName("input"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        Thread.sleep(500);

        jobDetails.click();
        Thread.sleep(500);
        jobDetails
                .findElement(By.tagName("input"))
                .sendKeys("N/A");
        Thread.sleep(500);

        saveButton.click();
        Thread.sleep(500);

//        Assert.assertEquals(helperMethods.currentDate(), checkInDate.getText(), "Check In date is not set to current date!");
//        Thread.sleep(500);
    }
}
