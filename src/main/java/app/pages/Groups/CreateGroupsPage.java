package app.pages.Groups;

import app.pages.MainPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import model.GroupsData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateGroupsPage extends MainPage {

    public SelenideElement nameField = $("input[name='title']");
    public SelenideElement nameFieldRequiredClass = nameField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement nameFieldRequiredClassError = nameFieldRequiredClass.find(By.xpath("//div[contains(@class, 'fieldset__label-invalid')]"));

    public SelenideElement descrField = $("input[name='description']");
    public SelenideElement descrFieldRequiredClass = descrField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));

    public SelenideElement rulesField = $(By.xpath("//form/fieldset[3]//div[contains(@class, 'fieldset__input')]//div[contains(@class, 'py-2')]"));
    public SelenideElement rulesFieldRequiredClass = rulesField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public ElementsCollection allRulesList = $$(By.xpath("//label[contains(@class, 'checkbox__container')]"));

    public SelenideElement usersField = $(By.xpath("//form/fieldset[4]//div[contains(@class, 'fieldset__input')]//div[contains(@class, 'py-2')]"));
    public SelenideElement usersFieldRequiredClass = usersField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public ElementsCollection allUsersList = $$(By.xpath("//label[contains(@class, 'checkbox__container')]"));

    public SelenideElement serviceAreaScreen = $(By.xpath("//div[contains(@class, '_Container_f')]"));

    public SelenideElement saveButton = $(By.xpath("//button[contains(@class, 'button_lg')]//span[text()='Сохранить']"));

    public SelenideElement successMessage = $(By.xpath("//div[text()='Успешно']"));

    public SelenideElement searchUserByNameField = $(By.xpath("//input[contains(@placeholder, 'Поиск по имени')]"));

    public CreateGroupsPage(String pageUrl) {
        super(pageUrl);
    }

    public CreateGroupsPage nameSetValue(String value){
        nameField.sendKeys(Keys.CONTROL + "A");
        nameField.sendKeys(Keys.DELETE);
        nameField.setValue(value);
        return this;
    }
    public String nameGetValue(){
        return nameField.getAttribute("value");
    }

    public CreateGroupsPage descriptionSetValue(String value){
        descrField.sendKeys(Keys.CONTROL + "A");
        descrField.sendKeys(Keys.DELETE);
        descrField.setValue(value);
        return this;
    }
    public String descriptionGetValue(){
        return descrField.getAttribute("value");
    }

    // Поле Ограничения
    public CreateGroupsPage rulesFieldClick(){
        rulesField.click();
        Driver.wait(1);
        return this;
    }

    public String rulesGetValue(){
        return rulesField.getText();
    }


    // Поле Пользователи
    public CreateGroupsPage userFieldClick(){
        usersField.click();
        Driver.wait(1);
        return this;
    }

    public String userGetValue(){
        return usersField.getText();
    }


    public CreateGroupsPage clickRuleByIndex(int index) {
        allRulesList.get(index - 1).click();
        Driver.wait(1);
        return this;
    }

    public CreateGroupsPage clickUserByIndex(int index) {
        allUsersList.get(index - 1).click();
        Driver.wait(1);
        return this;
    }

    public CreateGroupsPage clickUserByName(String name) {
        if (name != "") {
            searchUserByNameField.setValue(name);
            allUsersList.get(2).click();
        }
        Driver.wait(1);
        return this;
    }

    // Кнопка Сохранить
    public CreateGroupsPage saveButtonClick(){
        saveButton.click();
        Driver.wait(1);
        return this;
    }

    public CreateGroupsPage serviceAreaClick(){
        serviceAreaScreen.click(1,1);
        Driver.wait(1);
        return this;
    }

    public CreateGroupsPage successMessageClick(){
        if(successMessage.isEnabled())
            successMessage.click();
        return this;
    }

    public void checkEnabledCreateGroupsFields() {
        nameField.isEnabled();
        descrField.isEnabled();
        rulesField.isEnabled();
        usersField.isEnabled();
        saveButton.isEnabled();
        rulesField.click();
        allRulesList.get(0).isEnabled();
        serviceAreaClick();
        usersField.click();
        Driver.wait(1);
        allUsersList.get(0).isEnabled();
    }

    public void createGroup(GroupsData group){
        nameSetValue(group.getName());
        descriptionSetValue(group.getDescription());
        rulesFieldClick();
        clickRuleByIndex(Integer.valueOf(group.getRules()));
        serviceAreaClick();
        userFieldClick();
        clickUserByName(group.getUsers());
        serviceAreaClick();
        saveButtonClick();
    }

    public void editGroup(GroupsData group){
        nameSetValue(group.getName());
        descriptionSetValue(group.getDescription());
        rulesFieldClick();
        clickRuleByIndex(Integer.valueOf(group.getRules()));
        serviceAreaClick();
        userFieldClick();
        clickUserByName(group.getUsers());
        serviceAreaClick();
        saveButtonClick();
    }

    public GroupsData infoFromEditGroupForm(){
        String name = nameGetValue();
        String description = descriptionGetValue();
        String rules = rulesGetValue();
        String users = userGetValue();

        return new GroupsData().withName(name).withDescription(description).withRules(rules).withUsers(users);
    }
}
