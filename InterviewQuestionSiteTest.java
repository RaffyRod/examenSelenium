import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InterviewQuestionSiteTest {
    public static WebDriver driver;
   // public static timer =

    @BeforeTest
    public  void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(groups = "questions")
    public void landingPageTest(){
        driver.get("https://allstq.com/most-popular-testng-interview-questions/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

    }

//    @Test
//    public void getQuestionsTitles(){
//        driver.get("https://allstq.com/most-popular-testng-interview-questions/");
//        driver.manage().window().maximize();
//        List<WebElement> titleList = driver.findElements(By.tagName("h3"));
//       if( titleList.size() == 5){
//           System.out.println();
//       }
//
////        for( WebElement title: titleList){
////
////
////
////        }
//    }

    @AfterTest
    public void validateTest() throws InterruptedException {
        String expectedText  = "https://allstq.com/most-popular-testng-interview-questions/";
        String actualText = driver.getCurrentUrl();
        String expectedTextItem  = driver.findElement(By.linkText("Selenium")).getText();

        String actualItem = "Selenium";

        Assert.assertEquals(actualText, expectedText );
        Assert.assertEquals(actualItem, expectedTextItem);
        Thread.sleep(3000);
        driver.close();
    }

}
