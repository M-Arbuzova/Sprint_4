package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final By btnOrderUp = By.xpath(".//button[@class='Button_Button__ra12g']"); // кнопка "Заказать" сверху страницы
    private final By btnOrderMiddle = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button"); // кнопка "Заказать" посередине страницы
    private final By questionAnswerSection = By.xpath(".//div[text()='Вопросы о важном']"); // секция вопросов и ответов
    private final String questionLocator = "accordion__heading-"; // начало пути заголовка вопроса
    private final String textAnswerLocator = "accordion__panel-"; //начало пути заголовка ответа
    private final By btnCookie = By.className("App_CookieButton__3cvqF"); //кнопка принятия куки
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //показать текст ответа
    public String showTextAnswer(int index) {
        By textAnswerLocator = By.id("accordion__panel-" + index);
        return driver.findElement(textAnswerLocator).getText();
    }

    //проверка, что текст ответа отображается
    public boolean textAnswerIsDisplayed(int index) {
        By textAnswerLocator = By.id("accordion__panel-" + index);
        return driver.findElement(textAnswerLocator).isDisplayed();
    }

    //проверка, что текст вопроса кликабельный
    public boolean canClickQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        driver.findElement(questionLocator).click();
        return true;
    }

    // кнопка "Заказать" сверху страницы
    public void clickBtnOrderUp() {
        driver.findElement(btnOrderUp).click();
    }

    // кнопка "Заказать" сверху посередине страницы
    public void clickBtnOrderMiddle() {
        driver.findElement(btnOrderMiddle).click();
    }

    //проскроллить страницу до раздела "Вопросы о важном"
    public void findQuestionAnswerSection() {
        WebElement element1 = driver.findElement(questionAnswerSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
    }

    //проскроллить страницу до кнопки "Заказать" на середине страницы
    public void findBtnOrderMiddle() {
        WebElement element = driver.findElement(btnOrderMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //принять отслеживание куки
    public void clickAcceptCookie() {
        driver.findElement(btnCookie).click();
    }
}
