package ru.praktikum.scooter.pageobjects; // Исправлено на корректный пакет

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы
    private final By clientFirstName = By.xpath("//input[@placeholder='* Имя']");
    private final By clientLastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By deliveryAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By deliveryMetroStation = By.xpath("//input[@placeholder='* Станция метро']");
    private final By deliveryClientPhoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage sendClientFirstName(String firstName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(clientFirstName));
        firstNameField.sendKeys(firstName);
        return this;
    }

    public OrderPage sendClientLastName(String lastName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(clientLastName));
        lastNameField.sendKeys(lastName);
        return this;
    }

    public OrderPage sendDeliveryAddress(String address) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddress));
        addressField.sendKeys(address);
        return this;
    }

    public OrderPage selectMetroStation(String stationName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement metroStationField = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryMetroStation));
        metroStationField.click();
        metroStationField.sendKeys(stationName);
        metroStationField.sendKeys(Keys.DOWN, Keys.ENTER);
        return this;
    }

    public OrderPage sendDeliveryClientPhoneNumber(String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryClientPhoneNumber));
        phoneField.sendKeys(phoneNumber);
        return this;
    }

    public OrderPage clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextBtn.click();
        return this;
    }
}