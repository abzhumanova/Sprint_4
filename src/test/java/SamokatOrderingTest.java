import org.junit.Test;
import PageObjects.MainPage;
import PageObjects.OrderPage;
import PageObjects.RentPage;
import static org.junit.Assert.assertTrue;

public class SamokatOrderingTest extends CommonBaseTest {

    // Тест заказа через кнопку в шапке сервиса

    @Test
    public void samokatOrderingByHeaderOrderButton() {
        new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .clickHeaderOrderButton();

        // Заполнение первого экрана (OrderPage):
        new OrderPage(driver)
                .sendClientFirstName("Майя")
                .sendClientLastName("Иванова")
                .sendDeliveryAddress("Москва, Тюленева, 11")
                .selectMetroStation("Марьина Роща")
                .sendDeliveryClientPhoneNumber("89859858585")
                .clickNextButton();

        // Заполнение второго экрана (RentPage):
        boolean isDisplayed = new RentPage(driver)
                .sendRentalDate("28.03.2025")
                .setRentalTime()
                .clickCheckBoxColourBlackPearl()
                .sendComment("Пур пур пур пур!")
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();

        assertTrue("Окно заказа не появилось, очень жаль.", isDisplayed);
    }

    // Тест заказа через кнопку в середине страницы сервиса

    @Test
    public void samokatOrderingByMiddleOrderButton() {
        new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .clickMiddleOrderButton();

        new OrderPage(driver)
                .sendClientFirstName("Артем")
                .sendClientLastName("Мрзоев")
                .sendDeliveryAddress("Москва, Севанская, 16")
                .selectMetroStation("Павелецкая")
                .sendDeliveryClientPhoneNumber("87776996969")
                .clickNextButton();

        boolean isDisplayed = new RentPage(driver)
                .sendRentalDate("07.03.2023")
                .setRentalTime()
                .clickCheckBoxColourGreyDespair()
                .sendComment("Я хочу кататься, ехууууу!")
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();

        assertTrue("Окно заказа не появилось, не ехуууу :(", isDisplayed);
    }
}