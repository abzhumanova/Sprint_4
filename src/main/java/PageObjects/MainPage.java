package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By headerOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private final By middleOrderButton = By.xpath("//button[contains(@class,'Button_Button__ra12g Button_UltraBig__UU3Lp') and text()='Заказать']");

    // Вопросы и ответы в разделе «Вопросы о важном»
    private static final String[] QUESTIONS_ID = {
            "accordion__heading-0", "accordion__heading-1", "accordion__heading-2",
            "accordion__heading-3", "accordion__heading-4", "accordion__heading-5",
            "accordion__heading-6", "accordion__heading-7"
    };
    private static final String[] ANSWERS_ID = {
            "accordion__panel-0", "accordion__panel-1", "accordion__panel-2",
            "accordion__panel-3", "accordion__panel-4", "accordion__panel-5",
            "accordion__panel-6", "accordion__panel-7"
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public MainPage clickCookieButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        return this;
    }

    public MainPage clickHeaderOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
        return this;
    }

    public MainPage clickMiddleOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(middleOrderButton)).click();
        return this;
    }

    // Прокрутка страницы к последнему вопросу
    public MainPage scrollPageToEndOfList() {
        WebElement lastQuestion = driver.findElement(By.id(QUESTIONS_ID[QUESTIONS_ID.length - 1]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestion);
        return this;
    }

    // Клик по вопросу
    public MainPage clickQuestionArrow(int index) {
        driver.findElement(By.id(QUESTIONS_ID[index])).click();
        return this;
    }

    // Получение текста ответа
    public String getTextInOpenPanel(int index) {
        return driver.findElement(By.id(ANSWERS_ID[index])).getText();
    }
}