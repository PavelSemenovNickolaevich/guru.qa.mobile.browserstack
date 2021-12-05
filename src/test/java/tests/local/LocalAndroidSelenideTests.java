package tests.local;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("local_android")
public class LocalAndroidSelenideTests extends LocalTestBase {

    @Test
    @DisplayName("Successful search in wikipedia android app")
    void searchTest() {
        back();

        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Welcome screens")
    void checkScreensTest() {
        step("First screen check", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("The Free Encyclopedia …in over 300 languages"));
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).isEnabled();
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            sleep(1000);
        });
        step("Second screen check", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/imageViewCentered")).isDisplayed();
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).isEnabled();
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("New ways to explore"));
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            sleep(1000);
        });
        step("Three screen check", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/imageViewCentered")).isDisplayed();
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).isEnabled();
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("Reading lists with sync"));
            $(MobileBy.id("org.wikipedia.alpha:id/imageViewCentered")).isDisplayed();
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            sleep(1000);
        });
        step("Fourth screen check", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/imageViewCentered")).isDisplayed();
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("Send anonymous data"));
            $(MobileBy.id("org.wikipedia.alpha:id/switchView"))
                    .shouldHave(Condition.text("Send usage data")).isEnabled();
            $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"))
                    .shouldHave(Condition.text("GET STARTED")).isEnabled();
            sleep(1000);
        });
    }
}

