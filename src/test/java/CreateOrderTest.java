import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest extends TestBase {
    private final String orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String startDate;
    private final int orderDays;
    private final String color;
    private final String comments;
    private final String expectedOrderPlaced = "Заказ оформлен";

    public CreateOrderTest(String orderButton, String name, String surname, String address, String metro, String phone,
                           String startDate, int orderDays, String color, String comments) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.startDate = startDate;
        this.orderDays = orderDays;
        this.color = color;
        this.comments = comments;
    }

    @Parameterized.Parameters(name = "{index}: Тестовые данные: кнопка Заказать {0}, персонал. данные клиента {1},{2},{3}, {4}, {5}, данные о доставке {6}, {7}, {8}, {9}")
    public static Object[][] checkMakeOrder() {
        return new Object[][]{
                {"BtnOrderUp", "Мария", "Арбузова", "Осенний бульвар, 10", "Крыл", "79001001010", "10.03.2023", 1, "grey", "Доставить к подъезду"},
                {"BtnOrderMiddle", "Иван", "Иванов", "Парковая 5", "Алтуфьево", "89101001010", "23.04.2023", 2, "", ""},
        };
    }

    @Test
    public void canCreateOrder() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickAcceptCookie();
        //проверяем по условию какая кнопка должна быть использована, из этого система выбирает для клика нужную кнопку
        if ("BtnOrderUp".equals(orderButton)) {
            mainPage.clickBtnOrderUp();
        } else if ("BtnOrderMiddle".equals(orderButton)) {
            mainPage.findBtnOrderMiddle();
            mainPage.clickBtnOrderMiddle();
        }

        OrderPage orderPage = new OrderPage(driver);
        //заполняем поля первой страницы оформления заказа
        orderPage.setInfoAboutClient(name, surname, address, metro, phone);
        orderPage.clickBtnNextStepOrder();
        // заполняем поля второй страницы оформления заказа
        orderPage.setInfoAboutDelivery(startDate, orderDays, color, comments);
        //нажимаем кнопку оформить заказ и кнопку "Да"
        orderPage.clickBtnCreateOrder();
        orderPage.clickBtnCreateOrderYes();
        //если тест проходит, то ожидаем надпись "Заказ оформлен" и сравниваем ее с текущим текстом на странице после оформления заказа
        assertEquals(expectedOrderPlaced, orderPage.textOrderPlaced());
    }
}