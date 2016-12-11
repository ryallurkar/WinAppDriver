

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNotepad
{
    protected final String windowsApplicationDriverUrl = "http://127.0.0.1:4723";
    protected static RemoteWebDriver driver;
    protected static RemoteWebElement CalculatorResult;

    @BeforeClass
    public void setUpClass() throws MalformedURLException{
        // Launch the calculator app
    	
        DesiredCapabilities appCapabilities = new DesiredCapabilities();
        appCapabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
//        appCapabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        driver = new RemoteWebDriver(new URL(windowsApplicationDriverUrl),appCapabilities,appCapabilities);
        Assert.assertNotNull(driver);
//        driver.manage().timeouts().ImplicitlyWait(TimeSpan.FromSeconds(2));

        // Use series of operation to locate the calculator result text element as a workaround
        // We currently cannot query element by automationId without using modified appium dot net driver
        // TODO: Use a proper appium/webdriver nuget package that allow us to query based on automationId
        
//        driver.findElementByClassName("Edit").sendKeys("This is some text");
        
        driver.findElementByName("Clear").click();
        driver.findElementByName("Seven").click();
        CalculatorResult = (RemoteWebElement) driver.findElementByName("Display is  7 ");
        Assert.assertNotNull(CalculatorResult);
    }
    
    @AfterClass
    public void tearDownClass(){
        CalculatorResult = null;
        driver.quit();
        driver = null;
    }
    
    
    @Before
    public void Clear() {
        driver.findElementByName("Clear").click();
        Assert.assertEquals("Display is  0 ", CalculatorResult.getText());
    }
    
    @Test
    public void testAddition() {
        driver.findElementByName("One").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("Seven").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("Display is  8 ", CalculatorResult.getText());
    }
    
    @Test
    public void Combination() {
        driver.findElementByName("Seven").click();
        driver.findElementByName("Multiply by").click();
        driver.findElementByName("Nine").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Divide by").click();
        driver.findElementByName("Eight").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("Display is  8 ", CalculatorResult.getText());
    }

    @Test
    public void Division() {
        driver.findElementByName("Eight").click();
        driver.findElementByName("Eight").click();
        driver.findElementByName("Divide by").click();
        driver.findElementByName("One").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("Display is  7 ", CalculatorResult.getText());
    }
    
    public void Multiplication() {
        driver.findElementByName("Nine").click();
        driver.findElementByName("Multiply by").click();
        driver.findElementByName("Nine").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("Display is  81 ", CalculatorResult.getText());
    }

    @Test
    public void Subtraction() {
        driver.findElementByName("Nine").click();
        driver.findElementByName("Minus").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("Display is  8 ", CalculatorResult.getText());
    }
}