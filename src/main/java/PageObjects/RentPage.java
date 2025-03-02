package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    private final By rentalDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    private final By rentalTime = By.xpath("//div[@class='Dropdown-option' and text()='сутки']");
    private final By checkBoxColourBlackPearl = By.id("black");
    private final By checkBoxColourGreyDespair = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private final By orderButtonYes = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Да']");
    private final By modalOrderWindow = By.xpath("//div[contains(@class, 'Order_ModalHeader')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public RentPage sendRentalDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalDateField));
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
        return this;
    }

    public RentPage setRentalTime() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement timeField = wait.until(ExpectedConditions.elementToBeClickable(rentalTimeField));
        timeField.click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalTime)).click();
        return this;
    }

    public RentPage clickCheckBoxColourBlackPearl() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(checkBoxColourBlackPearl));
        checkBox.click();
        return this;
    }

    public RentPage clickCheckBoxColourGreyDespair() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(checkBoxColourGreyDespair));
        checkBox.click();
        return this;
    }

    public RentPage sendComment(String comment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement commentFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(commentField));
        commentFieldElement.sendKeys(comment);
        return this;
    }

    public RentPage clickOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        orderBtn.click();
        return this;
    }

    public RentPage clickOrderButtonYes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderYesBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButtonYes));
        orderYesBtn.click();
        return this;
    }

    public boolean isModalOrderWindowDisplayed() {
        return driver.findElement(modalOrderWindow).isDisplayed();
    }
}