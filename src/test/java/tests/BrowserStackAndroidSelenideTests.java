package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


@Tag("selenide_android")
public class BrowserStackAndroidSelenideTests extends TestBase {

    @Test
    @DisplayName("Successful search in wikipedia android app")
    void searchTest() {
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void articleCompareAndSwipeToFooterTest() {

        final String
                TITLE = "//*[@text='Java (programming language)']",
                FOOTER_ELEMENT = "//*[@text='View page in browser']",
                SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']";

        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("java");
        });
        step("Redirect to new article", () -> {
            String substringSearch = "Object-oriented programming language";
            String search_result_xpath = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substringSearch);
            $(MobileBy.xpath(search_result_xpath)).click();
        });
        step("Get and check article title", () -> {
            String title = $(MobileBy.xpath(TITLE)).getAttribute("text");
            Assertions.assertEquals( "Java (programming language)",title);
            $(MobileBy.xpath(TITLE)).getAttribute("text").equals(title);
        });
        step("Swipe to FOOTER", () -> {
            $(MobileBy.xpath(FOOTER_ELEMENT)).isDisplayed();
        });
    }
}