package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import org.testng.*;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    ChromeDriver driver;
    Wrappers wrap=new Wrappers(driver);

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */

     @Test(enabled = true)
     public void testCase01() throws InterruptedException
     {
        // wrap.openURL("https://www.flipkart.com/");
         WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
         
         
        driver.get("https://www.flipkart.com/");
         wrap.searchElementWrapper(driver,By.xpath("//input[@class='Pke_EE']"), "Washing Machine");
        WebElement Popularity_Button=driver.findElement(By.xpath("//span[@class='viJOii']//following-sibling::div[contains(text(),'Popular')]"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='viJOii']//following-sibling::div[contains(text(),'Popular')]")));
        wrap.clickElement(Popularity_Button);
        Thread.sleep(5000);
         List <WebElement> Ratings=driver.findElements(By.xpath("//span[contains(@id,'productRating_LSTWMN')]"));
        float[] rating=new float[Ratings.size()];
        int Item_Counter_Less_Than_4=0;
        for (int i=0;i<Ratings.size();i++) {
               
               rating[i]=Float.parseFloat(wrap.getTextfromElement(Ratings.get(i)));
               if(i<=4.0f)
               Item_Counter_Less_Than_4++;
            
        }
        System.out.println("The count of items with rating less than or equal to 4 stars is .." +Item_Counter_Less_Than_4);
        

     }
     @Test(enabled = true)
     public void testCase02() throws InterruptedException
     {
      driver.get("https://www.flipkart.com/");
      wrap.searchElementWrapper(driver,By.xpath("//input[@class='Pke_EE']"), "iPhone");
        Thread.sleep(2000);
        
        List<WebElement> Discounts=driver.findElements(By.xpath("//div[@class='UkUFwK']/ancestor::div[contains(@class,'yKfJKb')]"));
        for (WebElement dis : Discounts)
         {
 
          String DiscountPercentage=dis.findElement(By.xpath(".//div[@class='UkUFwK']/span")).getText();
           int discount=Integer.parseInt(DiscountPercentage.replaceAll("[^\\d]",""));
           if(discount>17)
           {
            String ProductTitle=dis.findElement(By.xpath(".//div[contains(@class,'KzDlHZ')]")).getText();
            System.out.println("The title of the product is :-"+ProductTitle+"Discount on the product is :-"+DiscountPercentage);
           }
          
      }
     }
    
    
    
    
     @Test(enabled =true)
     public void testCase03() throws InterruptedException
     {
      driver.get("https://www.flipkart.com/");
          wrap.searchElementWrapper(driver,By.xpath("//input[@class='Pke_EE']"), "Coffee Mug");

        WebElement Ratings_Checkbox=driver.findElement(By.xpath("//div[contains(@title,'4')]/descendant::div[@class='XqNaEv']"));
        wrap.clickElement(Ratings_Checkbox);
        Thread.sleep(2000);
        List <WebElement> Reviews=driver.findElements(By.xpath("//div[@class='slAVV4']//span[@class='Wphh3N']"));
         Set <Integer> reviewset=
         new HashSet<>() ;
        for (WebElement reviews : Reviews) {
          int review=Integer.parseInt(reviews.getText().replaceAll("[^\\d]",""));
          reviewset.add(review);
        }

        List<Integer> ReviewList=new ArrayList<>(reviewset);
        Collections.sort(ReviewList,Collections.reverseOrder());
        System.out.println(ReviewList);
        NumberFormat numberFormat=NumberFormat.getInstance(Locale.US);
        
        for(int i=0;i<5;i++)
        {
          String formattedUserReview="("+numberFormat.format(ReviewList.get(i))+")";
          String ProductTitle=driver.findElement(By.xpath("//span[contains(text(),'"+formattedUserReview+"')]/parent::div/preceding-sibling::a[@class='wjcEIp']")).getText();
          String ImageUrlText=driver.findElement(By.xpath("//span[contains(text(),'"+formattedUserReview+"')]/parent::div/preceding-sibling::a/descendant::img[@class='DByuf4']")).getAttribute("src");

          System.out.println("The title of  "+i+"product is :- " +ProductTitle +" The Image Url of the product is :- "+ImageUrlText);
        } 
     }

     
    
    
     @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");


        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}