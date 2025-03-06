import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; // Импортируем класс Duration
import ru.praktikum.scooter.pageobjects.MainPage; // Исправлено на корректный импорт

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DropDownListParamTest extends CommonBaseTest {

    private final String questionId;
    private final String expectedAnswer;

    public DropDownListParamTest(String questionId, String expectedAnswer) {
        this.questionId = questionId;
        this.expectedAnswer = expectedAnswer;
    }

    // Константы для тестовых данных
    private static final String[][] TEST_DATA = {
            {"accordion__heading-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"accordion__heading-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"accordion__heading-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"accordion__heading-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"accordion__heading-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"accordion__heading-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"accordion__heading-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"accordion__heading-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
    };

    @Parameterized.Parameters
    public static Object[][] answersData() {
        return TEST_DATA;
    }

    @Test
    public void testCheckDropDownQuestion() {
        MainPage mainPage = new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .scrollPageToEndOfList();

        // Кликаем по вопросу
        int questionIndex = Integer.parseInt(questionId.split("-")[1]); // Извлекаем индекс из questionId
        mainPage.clickQuestionArrow(questionIndex);

        // Получаем текст ответа с ожиданием
        String actualAnswer = getTextInOpenPanel(questionIndex);

        // Проверяем совпадение текста
        assertEquals("Текст ответа не соответствует ожидаемому", expectedAnswer, actualAnswer);
    }

    private String getTextInOpenPanel(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем, пока элемент ответа станет видимым
        WebElement answerPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + index)));

        // Ожидаем, пока текст в элементе не станет не пустым
        wait.until(driver -> {
            String text = answerPanel.getText().trim();
            return !text.isEmpty() ? answerPanel : null;
        });

        // Возвращаем текст, когда элемент видим и текст не пустой
        return answerPanel.getText().trim();
    }
}