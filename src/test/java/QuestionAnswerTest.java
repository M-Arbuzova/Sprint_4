import Pages.MainPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuestionAnswerTest extends TestBase{
    private final int index;
    private final boolean visibleAnswer;

    private final String answer;

    public QuestionAnswerTest(int index, boolean visibleAnswer, String answer) {
        this.index = index;
        this.visibleAnswer = visibleAnswer;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] checkQuestionAnswer() {
        return new Object[][]{
                {0, true, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, true, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, true, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, true, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, true, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, true, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, true, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, true, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},


        };
    }
    @Test
    public void canUseQuestionAnswer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAcceptCookie();
        mainPage.findQuestionAnswerSection();
        //проверяем, что текст вопроса кликабелен
        Assert.assertTrue(mainPage.canClickQuestion(index));
        //проверяем, что текст ответа отображается
        Assert.assertTrue(mainPage.textAnswerIsDisplayed(index));
        //проверяем, что текст ответа соответствует ТЗ (тем текстам, которые сейчас на сайте)
        assertEquals("Ожидаемый результат: текст на экране соответствует ТЗ", mainPage.showTextAnswer(index), answer);

    }
}