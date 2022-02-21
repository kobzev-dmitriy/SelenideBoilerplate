package app.pages.Groups;

import app.pages.MainPage;
import app.pages.Users.ListUsersPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ListGroupsPage extends MainPage {

    public ElementsCollection rowCountResult = $$(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')]"));

    public ElementsCollection allIdsGroupsList = $$(By.xpath("//div[contains(@class, 'td')][1]//span[contains(@class, 'td__text')]"));
    public ElementsCollection allNamesGroupsList = $$(By.xpath("//div[contains(@class, 'td')][2]//span[contains(@class, 'td__text')]"));
    public ElementsCollection allEditGroupsList = $$(By.xpath("//div[contains(@class, 'td')][3]//div[contains(@class, 'col')][1]//button[contains(@class, 'button')]//span[text()='Редактировать']"));
    public ElementsCollection allDeleteGroupsList = $$(By.xpath("//div[contains(@class, 'td')][3]//div[contains(@class, 'col')][2]//button[contains(@class, 'button')]//span[text()='Удалить']"));

    public SelenideElement createButton = $(By.xpath(".//span[text()='Создать']"));

    public SelenideElement searchGroupByNameField = $(By.xpath("//input[contains(@placeholder, 'Поиск по названию')]"));

    public SelenideElement emptySearchResultMsg = $(By.xpath("//span[text()='Нет данных для отображения']"));

    public SelenideElement idNameColumnGroups = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][1]"));

    public SelenideElement nameNameColumnGroups = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][2]"));

    public SelenideElement actionsNameColumnGroups = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][2]"));

    public SelenideElement acceptDeleteGroup = $(By.xpath("//button[contains(@class, 'button')]//span[text()='Да']"));
    public SelenideElement denialDeleteGroup = $(By.xpath("//button[contains(@class, 'button')]//span[text()='Нет']"));

    public SelenideElement idRow1ListGroups = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][1]//span[contains(@class, 'td__text')]"));

    public SelenideElement nameRow1ListGroups = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][2]//span[contains(@class, 'td__text')]"));

    public SelenideElement successMessageList = $(By.xpath("//div[text()='Успешно']"));

    public SelenideElement firstPagePagination = $(By.xpath("//button[text()='«']"));

    public SelenideElement prevPagePagination = $(By.xpath("//button[text()='‹']"));

    public SelenideElement nextPagePagination = $(By.xpath("//button[text()='›']"));

    public SelenideElement lastPagePagination = $(By.xpath("//button[text()='»']"));

    public SelenideElement fivePerListPagination = $(By.xpath("//option[text()='5']"));

    public SelenideElement twentyFivePerListPagination = $(By.xpath("//option[text()='25']"));

    public SelenideElement fiftyPerListPagination = $(By.xpath("//option[text()='50']"));

    public SelenideElement hundredPerListPagination = $(By.xpath("//option[text()='100']"));

    public ListGroupsPage(String pageUrl) {
        super(pageUrl);
    }

    public int countRowResult() {
        return rowCountResult.size();
    }

    public SelenideElement getIdsListByIndex(int index) {
        return allIdsGroupsList.get(index - 1);
    }

    private SelenideElement getNameListByIndex(int index) {
        return allNamesGroupsList.get(index - 1);
    }

    public ListGroupsPage clickEditListGroupsByIndex(int index) {
        allEditGroupsList.get(index - 1).click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage clickDeleteListGroupsByIndex(int index) {
        allDeleteGroupsList.get(index - 1).click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage createButtonClick(){
        createButton.click();
        Driver.wait(1);
        return this;
    }

    public String getIdGroupListByIndex(int index){ return getIdsListByIndex(index).getText(); }

    public String getNameGroupListByIndex(int index){ return getNameListByIndex(index).getText(); }

    public ListGroupsPage idGroupsSort(){
        idNameColumnGroups.click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage nameGroupsSort(){
        nameNameColumnGroups.click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage acceptDeleteGroupClick(){
        acceptDeleteGroup.click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage denialDeleteGroupClick(){
        denialDeleteGroup.click();
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage searchGroupByName(String string) {
        if (string == "") {
            searchGroupByNameField.sendKeys(Keys.CONTROL + "A");
            searchGroupByNameField.sendKeys(Keys.DELETE);
        } else {
            searchGroupByNameField.setValue(string);
        }
        Driver.wait(1);
        return this;
    }

    public ListGroupsPage emptySearchResultMsgEnabled(){
        emptySearchResultMsg.isEnabled();
        return this;
    }

    public ListGroupsPage acceptDeleteGroupEnabled(){
        acceptDeleteGroup.isEnabled();
        return this;
    }

    public ListGroupsPage denialDeleteGroupEnabled(){
        denialDeleteGroup.isEnabled();
        return this;
    }

    public void deleteGroupBySearchName(String name){
        searchGroupByName(name);
        clickDeleteListGroupsByIndex(1);
        acceptDeleteGroupClick();
        successMessageListClick();
    }

    public ListGroupsPage successMessageListClick(){
        if(successMessageList.isEnabled())
            successMessageList.click();
        return this;
    }

    public ListGroupsPage nextPagePaginationClick(){
        nextPagePagination.click();
        Driver.wait(1);
        return this;
    }

    public void checkEnabledListUsersFields() {
        idNameColumnGroups.isEnabled();
        nameNameColumnGroups.isEnabled();
        actionsNameColumnGroups.isEnabled();
        getIdsListByIndex(1).isEnabled();
        getNameListByIndex(1).isEnabled();
        allEditGroupsList.get(0).isEnabled();
        allDeleteGroupsList.get(0).isEnabled();
        createButton.isEnabled();
        searchGroupByNameField.isEnabled();
        firstPagePagination.isEnabled();
        prevPagePagination.isEnabled();
        nextPagePagination.isEnabled();
        lastPagePagination.isEnabled();
        fivePerListPagination.isEnabled();
        twentyFivePerListPagination.isEnabled();
        fiftyPerListPagination.isEnabled();
        hundredPerListPagination.isEnabled();
    }

    public ListGroupsPage lastPagePaginationClick(){
        lastPagePagination.click();
        return this;
    }
}
