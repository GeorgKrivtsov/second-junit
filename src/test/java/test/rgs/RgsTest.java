package test.rgs;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import test.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class RgsTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"Георгий", "Алексей", "Дмитрий"})
    public void test() {
        //Переходим на сайт
        driver.get("http://www.rgs.ru");

        //Ждем и закрываем всплывающее окно
        //closeFrame(By.id("fl-616371"));

        //Закрываем куки
        WebElement cookie = driver.findElement(By.xpath("//button[@class='btn--text']"));
        cookie.click();

        //closeFrame(By.id("fl-616371"));

        //нажимаем кнопку "Компаниям"
        WebElement baseMenu = driver.findElement(By.xpath("//a[@href='/for-companies' and @class='text--second']"));
        baseMenu.click();

        driver.switchTo().frame(driver.findElement(By.id("fl-616371")));
        WebElement closePoint = driver.findElement(By.xpath("//div[@data-fl-track='click-close-login']"));
        try {
            closePoint.click();
        } catch (ElementNotInteractableException ignore) {

        } finally {
            driver.switchTo().defaultContent();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //Проверка загрузки страницы
        WebElement titleCompany = driver.findElement(By.xpath("//li[@id='thumb-0']"));
        Assert.assertTrue("Страничка не загрузилась", titleCompany.isDisplayed() &&
                titleCompany.getText().contains("Ответственность проектировщиков"));


        //открываем вкладку "Здоровье"
        WebElement subMenu = driver.findElement(By.xpath("//span[text()='Здоровье']"));
        subMenu.click();

        //Проверка загрузки всплывающего меню
        WebElement parentSubMenu = subMenu.findElement(By.xpath("./.."));
        Assert.assertTrue("Клик не был совершен", parentSubMenu.getAttribute("class").contains("active"));

        //нажимаем на кнопку ДМС
        WebElement healthButton = driver.findElement(By.xpath("//a[@href='/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie'] "));
        healthButton.click();

        //Проверка загрузки страницы
        WebElement titleHeader = driver.findElement(By.xpath("//h1[@class='title word-breaking title--h2']"));
        Assert.assertEquals("Страница здоровье не открыта", "Добровольное медицинское страхование",
                titleHeader.getText());

        //Нажатие на кнопку "отправить заявку"
        WebElement buttonSent = driver.findElement(By.xpath("//button[@type='button' and @class='action-item btn--basic']"));
        buttonSent.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Заполняем форму
//        WebElement userName = driver.findElement(By.xpath("//input[@name='userName']"));
//        userName.sendKeys("Георгий");
//        WebElement phoneNumber = driver.findElement(By.xpath("//input[@name='userTel']"));
//        phoneNumber.sendKeys("800-000-0000");
//        WebElement userEmail = driver.findElement(By.xpath("//input[@name='userEmail']"));
//        userEmail.sendKeys("qwertyqwerty");
//
//        //Заполняем адрес
        WebElement userAddress = driver.findElement(By.xpath("//input[@type='text' and @class='vue-dadata__input']"));
        userAddress.sendKeys("Краснодарский край, г Сочи, ул Тимирязева, д 11");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String temp = driver.getPageSource();

        List<WebElement> listAdress = driver.findElements(By.xpath("//span[@class = 'vue-dadata__suggestions-item']"));

        listAdress.stream().forEach(item -> System.out.println(item.getText()));

        listAdress.get(0).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//
//        scrollWithOffset(driver.findElement(By.xpath("//input[@type='checkbox']")), 0, 250);
//        //WebElement checkBox = driver.findElement(By.id("data-v-62a801c6"));
//        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']/.."));
//        checkBox.click();
//
//        //Нажимаем кнопку "Свяжитесь со мной"
//        WebElement buttonConnect = driver.findElement(By.xpath("//button[@type='submit' and text()='Свяжитесь со мной']"));
//        buttonConnect.click();
//
//        //Проверка сообщения об ошибке
//        WebElement errorText = driver.findElement(By.xpath("//input[@name='userEmail']/../../span[contains(@class, error)]"));
//        Assert.assertEquals("не правильный текст ошибки", "Введите корректный адрес электронной почты", errorText.getText());

    }

//    @ParameterizedTest
//    @ValueSource(strings = {"Георгий", "Алексей", "Дмитрий"})
//    public void test_userName(String s) {
//        driver.get("https://www.rgs.ru/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie");
//        WebElement userName = driver.findElement(By.xpath("//input[@name='userName' and @type='Text']"));
//
//        userName.sendKeys(s);
//        Assert.assertEquals("Текст не совпадает", userName.getText(), s);
//    }

public boolean isExistsFrame(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.switchTo().frame(driver.findElement(by));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
}

public void closeFrame(By by) {
        if (isExistsFrame(by)) {
            driver.switchTo().frame(driver.findElement(by));
            WebElement closePoint = driver.findElement(By.xpath("//div[@data-fl-track='click-close-login']"));
            try {
                closePoint.click();
            } catch (ElementNotInteractableException ignore) {

            } finally {
                driver.switchTo().defaultContent();
            }


            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

}






    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driver).executeScript(code, element, x, y);
        return element;
    }
}
