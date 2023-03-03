package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final By inputNameClient = By.xpath("//input[@placeholder='* Имя']");
    private final By inputSurnameClient = By.xpath("//input[@placeholder='* Фамилия']");
    private final By inputAdressClient = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By listMetroStation = By.xpath("//input[@placeholder='* Станция метро']");
    private final By inputPhoneNumberClient = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By btnNextStepOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By btnReturnStep = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    private final By dropDateDelivery = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By btnPeriodRent = By.xpath(".//div[text() = '* Срок аренды']");
    private final By dropListPeriodRent = By.xpath(".//div[@class = 'Dropdown-option']"); //стрелка для выбора периода аренды
    private final By checkboxColourBlackPearl = By.xpath("//input[@id='black']");
    private final By checkboxColourGrayHopelessness = By.xpath("//input[@id='grey']");
    private final By inputCommentCourier = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By btnCreateOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //кнопка подтверждения оформления заказа
    private final By btnCreateOrderYes = By.xpath("//button[contains(text(), 'Да')]");
    private final By btnCreateOrderNo = By.xpath("//button[contains(text(), 'Нет')]");
    private final By containerOrderPlaced = By.className("Order_ModalHeader__3FDaJ"); // подтверждение, что заказ оформлен
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameClient(String text) {
        driver.findElement(inputNameClient).sendKeys(text);
    }

    public void setSurnameClient(String text) {
        driver.findElement(inputSurnameClient).sendKeys(text);
    }

    public void setAdressClient(String text) {
        driver.findElement(inputAdressClient).sendKeys(text);
    }

    public void setInputPhoneNumberClient(String text) {
        driver.findElement(inputPhoneNumberClient).sendKeys(text);
    }

    public void setListMetroStation(String text) {
        driver.findElement(listMetroStation).click();
        driver.findElement(listMetroStation).sendKeys(text, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void clickBtnNextStepOrder() {
        driver.findElement(btnNextStepOrder).click();
    }

    public void setDropDateDelivery(String text) {
        driver.findElement(dropDateDelivery).click();
        driver.findElement(dropDateDelivery).sendKeys(text, Keys.ENTER);
    }

    public void setPeriodRent(int day) {
        driver.findElement(btnPeriodRent).click();
        driver.findElements(dropListPeriodRent).get(day).click();
    }

    public void setColorCheckbox(String color) {
        if ("black".equals(color)) {
            driver.findElement(checkboxColourBlackPearl).click();
        } else if ("grey".equals(color)) {
            driver.findElement(checkboxColourGrayHopelessness).click();
        }
    }

    public void setInputCommentCourier(String comments) {
        driver.findElement(inputCommentCourier).sendKeys(comments);
    }

    public void clickBtnReturnStep() {
        driver.findElement(btnReturnStep).click();
    }

    public void clickBtnCreateOrder() {
        driver.findElement(btnCreateOrder).click();
    }

    public void clickBtnCreateOrderYes() {
        driver.findElement(btnCreateOrderYes).click();
    }

    public void clickBtnCreateOrderNo() {
        driver.findElement(btnCreateOrderNo).click();
    }

    //получаем текст окна после оформления заказа
    public String textOrderPlaced() {
        return driver.findElement(containerOrderPlaced).getText();
    }

    public void setInfoAboutClient(String name, String surname, String address, String metro, String phone) {
        setNameClient(name);
        setSurnameClient(surname);
        setAdressClient(address);
        setListMetroStation(metro);
        setInputPhoneNumberClient(phone);
    }

    public void setInfoAboutDelivery(String startDate, int orderDays, String color, String comments) {
        setDropDateDelivery(startDate);
        setPeriodRent(orderDays);
        setColorCheckbox(color);
        setInputCommentCourier(comments);
    }
}
