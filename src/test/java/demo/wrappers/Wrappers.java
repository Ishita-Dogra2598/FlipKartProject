package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    ChromeDriver driver;
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));



    public void ExplicitWait()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));

    }

     public Wrappers(ChromeDriver driver)
     {
       
        this.driver=driver;
     }
      public void openURL(String url)
      {
        driver.get(url);
      }
     public void clickElement(WebElement element)
     {
        element.click();
     }
     public void enterInput(String input,WebElement element)
     {
        element.sendKeys(input);
     }
     public String getTextfromElement(WebElement element)
     {
        element.getText();
        return element.getText();
     }

     public void  searchElementWrapper(WebDriver driver,By locator,String input)
     {
        WebElement element=driver.findElement(locator);
        element.sendKeys(input);
         element.sendKeys(Keys.ENTER);
     }


    


   }
