package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTestPositive() {
        String date = generateDate(4);
        $x("//input[@placeholder='Город']").setValue("Архангельск");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $x("//input[@name='name']").setValue("Петрова-Васечкина Анна");
        $x("//input[@name='phone']").setValue("+79001112233");
        $x("//span[@class='checkbox__text']").click();
        $x("//*[text()='Забронировать']").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//div[@class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + date));
    }

}
