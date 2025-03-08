package ru.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Константа для URL
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button");

    // Локаторы для кнопок заказа
    private final By headerOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private final By middleOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_UltraBig__UU3Lp') and text()='Заказать']");

    // Локаторы для вопросов и ответов в разделе «Вопросы о важном»
    private final By[] QUESTIONS_LOCATORS = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    private final By[] ANSWERS_LOCATORS = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MainPage openSite() {
        driver.get(BASE_URL);
        return this;
    }

    public MainPage clickCookieButton() {
        clickElement(cookieButton);
        return this;
    }

    public MainPage clickHeaderOrderButton() {
        clickElement(headerOrderButton);
        return this; // Возвращаем текущий объект для поддержки цепочек вызовов
    }

    public MainPage clickMiddleOrderButton() {
        clickElement(middleOrderButton);
        return this; // Возвращаем текущий объект для поддержки цепочек вызовов
    }

    // Прокрутка страницы к последнему вопросу
    public MainPage scrollPageToEndOfList() {
        WebElement lastQuestion = driver.findElement(QUESTIONS_LOCATORS[QUESTIONS_LOCATORS.length - 1]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestion);
        return this;
    }

    // Клик по вопросу
    public MainPage clickQuestionArrow(int index) {
        clickElement(QUESTIONS_LOCATORS[index]);
        return this;
    }

    // Получение текста ответа
    public String getTextInOpenPanel(int index) {
        WebElement answerPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(ANSWERS_LOCATORS[index]));
        return answerPanel.getText().trim();
    }

    // Вспомогательный метод для клика по элементу
    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}