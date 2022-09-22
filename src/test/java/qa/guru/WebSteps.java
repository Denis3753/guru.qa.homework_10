package qa.guru;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открывыаем главную страницу")
    public void openMainPage(){
        open("https://github.com");
    }

    @Step("Ищем репозиторий на GitHub {repo}")
    public void searchForRepository(String repo){
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo){
        $(linkText(repo)).click();
    }

    @Step("Открываем tab issue")
    public void openIssuesTab(){
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие issue c номером {issue}")
    public void shouldIssueWithNumber(int issue){
        $(withText("#" + issue)).should(Condition.exist);
    }

}
