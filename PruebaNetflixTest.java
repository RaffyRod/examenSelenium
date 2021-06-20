import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class PruebaNetflixTest {

        public static WebDriver driver;
        // public static timer =

        @BeforeTest
        public  void beforeTest(){
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.netflix.com/do-es");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

        @Test(groups = "netflix")
        public void iniciarSesionPageTest() throws InterruptedException {

            driver.findElement(By.linkText("Iniciar sesión")).click();
            Thread.sleep(2000);
            String actualText = driver.findElement(By.className("btn-submit")).getText();
            String expectedTextItem  = "Sign In";
            Assert.assertEquals(actualText, expectedTextItem);

            String fbLogin = driver.findElement(By.className("fbBtnText")).getText();
            String expectedLogin = "Login with Facebook";

            Assert.assertEquals(fbLogin, expectedLogin);
            Thread.sleep(2000);

        }

        @Test(groups = "netflix")
        public void fakeEmailTest() throws InterruptedException {
            //driver.findElement(By.linkText("Iniciar sesión")).click();
            driver.get("https://www.netflix.com/do-en/");
            Thread.sleep(2000);
            Faker faker = new Faker();
            String fakeEmail = faker.internet().emailAddress();
            driver.findElement(By.name("email")).sendKeys(fakeEmail);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Thread.sleep(4000);

            String expectedUrl = "https://www.netflix.com/signup/registration?locale=en-DO";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(expectedUrl, actualUrl);
            driver.findElement(By.xpath("//button[@type='button']")).click();

            Thread.sleep(4000);

            String signUpUrl = "https://www.netflix.com/signup/regform";
            String actualUrl2 = driver.getCurrentUrl();
            Assert.assertEquals(signUpUrl, actualUrl2);

        }

        @DataProvider( name= "emails")
        public Object[][] crearEmails(){
            return new Object[][]{
                    {"alguien1@gmail.com"},
                    {"alguien2@hotmail.com"},
                    {"alguien3@outlook.com"}
            };
        }

        @Test(dataProvider = "emails" , groups = "netflix")
        public void dataProviderEmailTest(String email){
            driver.get("https://www.netflix.com/do-en/");
            driver.findElement(By.name("email")).sendKeys(email);

        }

        @AfterTest
        public void endTest() throws InterruptedException {
            Thread.sleep(3000);
            driver.close();
        }
}

