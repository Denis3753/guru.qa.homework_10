package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideAndLambdaIssueTabTest {

    @BeforeAll
    static void configuration() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://github.com";
    }

    public static final int ISSUE_NUMBER = 822;
    private static final String REPOSITORY = "allure-framework/allure-java";


    @Test
    public void simpleSelenideIssueTest(){
        open("/");
        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);

    }

    @Test
    void issueTabLambdaTest() {
        step("Открываем страницу github.com", () -> {
            open("/");
        });

        step("Ищем репозиторий: " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Открываем репозиторий: " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие issue с #" + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        });
    }

    @Test
    void issueTabWebStepsTest() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldIssueWithNumber(ISSUE_NUMBER);
    }

}
