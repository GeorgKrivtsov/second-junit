package test.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

//    @BeforeAll
//    static void beforeClass() {
//
//    }

    @BeforeEach
    public void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //развернуть браузер
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Переходим на сайт
        driver.get("http://www.rgs.ru");
    }


    @AfterEach
    void after() {
        driver.quit();
    }

}
