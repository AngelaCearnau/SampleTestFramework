package com.one.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.function.Supplier;

import static com.one.locators.XPathSelector.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface FormElements extends ExplicitWait {

    default void setInputText(Supplier<By> by, Object value) {
        Retry retry = new Retry(5, 1, SECONDS);

        retry.attempt(
            () -> {
                Element element = await(by);
                element.sendKeys(value.toString());
            }
        );
    }

    default void deleteTextFromKeyboard(Supplier<By> by) {
        Element element = await(by);
        element.sendKeys(Keys.CONTROL,"a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    default String getInputText(Supplier<By> by) {
        return await(by).getValue();
    }

}