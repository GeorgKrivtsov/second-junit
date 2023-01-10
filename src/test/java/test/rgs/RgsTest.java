package test.rgs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import test.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class RgsTest extends BaseTest {


    @ParameterizedTest
    @Tag("Smoke")
    @ValueSource(strings = {"Кривцов Георгий Алексеевич", "Левин Артем Викторович", "Денисов Михаил Николаевич"})
    public void test(String s) {


        //Ждем и закрываем всплывающее окно
        closeFrame(By.id("fl-616371"));

        //Закрываем куки
        WebElement cookie = driver.findElement(By.xpath("//button[@class='btn--text']"));
        cookie.click();

        //нажимаем кнопку "Компаниям"
        WebElement baseMenu = driver.findElement(By.xpath("//a[@href='/for-companies' and @class='text--second']"));
        baseMenu.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        closeFrame(By.id("fl-616371"));

//        driver.switchTo().frame(driver.findElement(By.id("fl-616371")));
//        WebElement closePoint = driver.findElement(By.xpath("//div[@data-fl-track='click-close-login']"));
//        try {
//            closePoint.click();
//        } catch (ElementNotInteractableException ignore) {
//
//        } finally {
//            driver.switchTo().defaultContent();
//        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //Проверка загрузки страницы
        WebElement titleCompany = driver.findElement(By.xpath("//li[@id='thumb-0']"));
        Assertions.assertTrue(titleCompany.isDisplayed() &&
                        titleCompany.getText().contains("Ответственность проектировщиков"), "Страничка не загрузилась");


        //открываем вкладку "Здоровье"
        WebElement subMenu = driver.findElement(By.xpath("//span[text()='Здоровье']"));
        subMenu.click();

        //Проверка загрузки всплывающего меню
        WebElement parentSubMenu = subMenu.findElement(By.xpath("./.."));
        Assertions.assertTrue(parentSubMenu.getAttribute("class").contains("active"), "Клик не был совершен");

        //нажимаем на кнопку ДМС
        WebElement healthButton = driver.findElement(By.xpath("//a[@href='/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie'] "));
        healthButton.click();

        //Проверка загрузки страницы
        WebElement titleHeader = driver.findElement(By.xpath("//h1[@class='title word-breaking title--h2']"));
        Assertions.assertEquals("Добровольное медицинское страхование",
                titleHeader.getText(),"Страница здоровье не открыта");

        //Нажатие на кнопку "отправить заявку"
        WebElement buttonSent = driver.findElement(By.xpath("//button[@type='button' and @class='action-item btn--basic']"));
        buttonSent.click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Заполняем форму
        //Заполняем имя
        WebElement userName = driver.findElement(By.xpath("//input[@name='userName']"));
        userName.sendKeys(s);
        //Заполняем телефон
        WebElement phoneNumber = driver.findElement(By.xpath("//input[@name='userTel']"));
        phoneNumber.sendKeys("800-000-0000");

        //Заполняем адрес
        WebElement userAddress = driver.findElement(By.xpath("//input[@type='text' and @class='vue-dadata__input']"));
        userAddress.sendKeys("Краснодарский край, г Сочи, ул Тимирязева, д 11");

        //заполняем почту
        WebElement userEmail = driver.findElement(By.xpath("//input[@name='userEmail']"));
        userEmail.sendKeys("qwertyqwerty");

        //Проверка заполнения формы "ФИО"
        Assertions.assertEquals(userName.getText(), s, "Форма заполнилась не верно");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        String temp = driver.getPageSource();
//
//        List<WebElement> listAdress = driver.findElements(By.xpath("//span[@class = 'vue-dadata__suggestions-item']"));
//
//        listAdress.stream().forEach(item -> System.out.println(item.getText()));
//
//        listAdress.get(0).click();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //Ставим галочку
        scrollWithOffset(driver.findElement(By.xpath("//input[@type='checkbox']")), 0, 300);
        //WebElement checkBox = driver.findElement(By.id("data-v-62a801c6"));
        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']/.."));
        checkBox.click();

        //Нажимаем кнопку "Свяжитесь со мной"
        WebElement buttonConnect = driver.findElement(By.xpath("//button[@type='submit' and text()='Свяжитесь со мной']"));
        buttonConnect.click();

        //Проверка сообщения об ошибке
        WebElement errorText = driver.findElement(By.xpath("//input[@name='userEmail']/../../span[contains(@class, error)]"));
        Assertions.assertEquals("Введите корректный адрес электронной почты", errorText.getText(), "не правильный текст ошибки");

    }

//    public boolean isExistsFrame(By by) {
//            try {
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//                driver.switchTo().frame(driver.findElement(by));
//                return true;
//            } catch (NoSuchElementException e) {
//                return false;
//            } finally {
//                driver.switchTo().defaultContent();
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//            }
//}
//
//    public void closeFrame(By by) {
//            if (isExistsFrame(by)) {
//                driver.switchTo().frame(driver.findElement(by));
//                WebElement closePoint = driver.findElement(By.xpath("//div[@data-fl-track='click-close-login']"));
//                try {
//                    closePoint.click();
//                } catch (ElementNotInteractableException ignore) {
//
//                } finally {
//                    driver.switchTo().defaultContent();
//                }
//
//
//                try {
//                    Thread.sleep(3);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//    }



    public boolean isExistsFrame(By by) {
        try {
            driver.switchTo().frame(driver.findElement(by));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();

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
