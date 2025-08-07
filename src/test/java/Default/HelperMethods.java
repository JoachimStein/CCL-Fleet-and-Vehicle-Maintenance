package Default;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperMethods {

    public String currentDate() {
        LocalDate myDate = LocalDate.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return myDate.format(formattedDate);
    }

    public void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("D:\\CG Documents\\CCL\\Transport and Trip Management\\AutomationScripts\\src\\test\\Screenshots\\"+ fileName + ".png"));
    }
}
