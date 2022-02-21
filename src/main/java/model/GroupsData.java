package model;

public class GroupsData {
    private String nameField;

    private String descriptionField;

    private String rulesField;

    private String usersField;

    public GroupsData withName(String nameField){
        this.nameField = nameField;
        return this;
    }

    public GroupsData withDescription (String descriptionField){
        this.descriptionField = descriptionField;
        return this;
    }

    public GroupsData withRules (String rulesField){
        this.rulesField = rulesField;
        return this;
    }

    public GroupsData withUsers (String usersField){
        this.usersField = usersField;
        return this;
    }

    public String getName(){
        return nameField;
    }
    public String getDescription() { return descriptionField; }
    public String getRules() { return rulesField; }
    public String getUsers() { return usersField; }
}
