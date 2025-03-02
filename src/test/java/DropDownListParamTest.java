import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageObjects.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DropDownListParamTest extends CommonBaseTest {

    private final String questionId;
    private final String answerId;
    private final String expectedAnswer;

    public DropDownListParamTest(String questionId, String answerId, String expectedAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] answersData() {
        return new Object[][]{
                {"accordion__heading-0", "accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "accordion__panel-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "accordion__panel-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "accordion__panel-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "accordion__panel-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "accordion__panel-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void checkDropDownQuestion() {
        new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .scrollPageToEndOfList();

        // Кликаем по вопросу
        WebElement questionElement = driver.findElement(By.id(questionId));
        questionElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Ожидаем, когда панель ответа появится
        WebElement answerPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));

        // Ожидаем, пока текст в ответе не станет ненулевым
        wait.until(driver -> {
            String text = answerPanel.getText().trim();
            System.out.println("Текущий текст ответа: " + text); // Отладочный вывод
            return !text.isEmpty(); // Ждем, пока текст не станет ненулевым
        });

        // Переполучаем текст элемента ответа перед проверкой
        String actualAnswer = answerPanel.getText().trim();

        // Если getText() не работает должным образом, используем JavaScript для получения текста
        if (actualAnswer.isEmpty()) {
            actualAnswer = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", answerPanel);
        }

        // Отладочный вывод
        System.out.println("Фактический ответ (перепроверка): " + actualAnswer);
        System.out.println("Ожидаемый ответ: " + expectedAnswer);
        System.out.println("HTML ответа: " + answerPanel.getAttribute("outerHTML")); // Вывод HTML для отладки

        // Проверяем совпадение текста
        assertEquals("Текст ответа не соответствует ожидаемому", expectedAnswer, actualAnswer);
    }
}