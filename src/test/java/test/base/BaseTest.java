package test.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    @BeforeAll
    static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        driver.manage().window().maximize(); //развернуть браузер
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

//    @AfterAll
//    static void afterClass() {
//        driver.quit();
//    }

}
