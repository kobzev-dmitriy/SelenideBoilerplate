import com.codeborne.selenide.testng.TextReport;
import helpers.Driver;
import model.GroupsData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.codeborne.selenide.testng.ScreenShooter;

@Listeners({ ScreenShooter.class, TextReport.class})
public class GroupsFunctTests extends A_BaseTest{
     @Test
    // 3925 Группы. Наличие полей, внешний вид модуля
    public void B_formModuleGroups() {
        app.mainPage.openGroups();
        Driver.refresh();
        Assert.assertTrue(app.listGroupsPage.countRowResult() > 0);
        app.listGroupsPage.checkEnabledListUsersFields();
        Assert.assertEquals(app.listGroupsPage.idNameColumnGroups.getText().trim(), "id");
        Assert.assertEquals(app.listGroupsPage.nameNameColumnGroups.getText().trim(), "Название");

        app.listGroupsPage.createButtonClick();
        Assert.assertEquals(app.createGroupsPage.nameFieldRequiredClass.getText().trim(), "Название");
        Assert.assertEquals(app.createGroupsPage.descrFieldRequiredClass.getText().trim(), "Описание");
        Assert.assertEquals(app.createGroupsPage.rulesFieldRequiredClass.getText().trim(), "Ограничения");
        Assert.assertEquals(app.createGroupsPage.usersFieldRequiredClass.getText().trim(), "Пользователи");
        app.createGroupsPage.checkEnabledCreateGroupsFields();
    }
    /*
    @Test
    // 3900 Группы. Создание
    public void C_createGroup() {
        GroupsData groupData = new GroupsData()
                .withName("TestCreate");
        app.mainPage.openGroups();
        // Нажать создать. Открыто окно создания
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(1), "Администратор");
        app.listGroupsPage.createButtonClick();
        // Ввести валидные данные во все поля помеченные * (Поле "Название")
        app.createGroupsPage.createGroup(groupData);
        app.createGroupsPage.successMessageClick();

        app.mainPage.openGroups();
        app.listGroupsPage.lastPagePaginationClick();
        // Группа отображается в списке
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(app.listUsersPage.countRowResult()), groupData.getName());
        Driver.refresh();
        app.listGroupsPage.lastPagePaginationClick();
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(app.listUsersPage.countRowResult()), groupData.getName());

        // Редактировать ее, проверить, что заданные значения сохранились
        app.listGroupsPage.clickEditListGroupsByIndex(app.listUsersPage.countRowResult());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getName(), groupData.getName());

        app.mainPage.openGroups();
        app.listGroupsPage.deleteGroupBySearchName("TestCreate");
    }

    @Test
    // 3901 Группы. Редактирование группы
    public void D_editGroup() {
        // Создаем группу
        GroupsData groupData = new GroupsData()
                .withName("TestEdit").withDescription("TestEdit").withRules("2").withUsers("admin1");
        app.mainPage.openGroups();
        app.listGroupsPage.createButtonClick();
        app.createGroupsPage.createGroup(groupData);
        app.createGroupsPage.successMessageClick();

        app.mainPage.openGroups();
        app.listGroupsPage.searchGroupByName("TestEdit");
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(app.listUsersPage.countRowResult()), groupData.getName());
        app.listGroupsPage.clickEditListGroupsByIndex(1);
        // Проверяем значения полей группы в форме редактирования
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getName(), groupData.getName());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getDescription(), groupData.getDescription());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getRules(), "Пользователи - Создание");
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getUsers(), "admin1");

        // Редактировать группу. Изменить значения всех полей
        GroupsData groupData2 = new GroupsData()
                .withName("TestEdit2").withDescription("TestEdit2").withRules("2").withUsers("admin1");
        app.mainPage.openGroups();
        app.listGroupsPage.searchGroupByName("TestEdit");
        app.listGroupsPage.clickEditListGroupsByIndex(1);
        app.createGroupsPage.editGroup(groupData2);

        // Проверяем изменение значений полей группы в форме редактирования
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getName(), groupData2.getName());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getDescription(), groupData2.getDescription());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getRules(), "");
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getUsers(), "");

        Driver.refresh();
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getName(), groupData2.getName());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getDescription(), groupData2.getDescription());
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getRules(), "");
        Assert.assertEquals(app.createGroupsPage.infoFromEditGroupForm().getUsers(), "");

        app.mainPage.openGroups();
        app.listGroupsPage.lastPagePaginationClick();
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(app.listUsersPage.countRowResult()), groupData2.getName());

        app.mainPage.openGroups();
        app.listGroupsPage.deleteGroupBySearchName("TestEdit2");
    }

    @Test
    // Группы. Удаление группы
    public void E_deleteGroup(){
        // Регистрируем группу без пользователей
        GroupsData groupData = new GroupsData()
                .withName("TestDelete").withDescription("Description").withRules("2").withUsers("");
        app.mainPage.openGroups();
        app.listGroupsPage.createButtonClick();
        app.createGroupsPage.createGroup(groupData);
        app.createGroupsPage.successMessageClick();

        app.mainPage.openGroups();
        app.listGroupsPage.searchGroupByName("TestDelete");

        // Нажать Удалить у этой группы. Отображается окно подтверждения с выбором Да/Нет
        app.listGroupsPage.clickDeleteListGroupsByIndex(1);
        app.listGroupsPage.acceptDeleteGroupEnabled();
        app.listGroupsPage.denialDeleteGroupEnabled();

        // Нажать Нет. Окно закрылось, группа отображается в списке
        app.listGroupsPage.denialDeleteGroupClick();
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(1), "TestDelete");
        // Обновить страницу. Без изменений
        Driver.refresh();
        app.listGroupsPage.lastPagePaginationClick();
        Assert.assertEquals(app.listGroupsPage.getNameGroupListByIndex(app.listGroupsPage.countRowResult()), "TestDelete");

        // Снова нажать Удалить, выбрать Да
        app.listGroupsPage.clickDeleteListGroupsByIndex(app.listGroupsPage.countRowResult());
        app.listGroupsPage.acceptDeleteGroupClick();
        // Группы нет в списке
        app.listGroupsPage.searchGroupByName("TestDelete");
        app.listGroupsPage.emptySearchResultMsgEnabled();
        // Обновить страницу по CTRL+F5. Без изменений
        Driver.refresh();
        app.listGroupsPage.searchGroupByName("TestDelete");
        app.listGroupsPage.emptySearchResultMsgEnabled();
    }
    */
}
