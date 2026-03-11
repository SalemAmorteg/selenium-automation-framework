package pages;

import base.BasePage;
import driver.DriverManager;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class UsersPage extends BasePage {

    // 🔹 Locators
    private By usersPageIdentifier = By.xpath("//h1[contains(text(), 'Gestión de Usuarios')]");

    private By createUserButton = By.id("add-user-btn");

    private By userList = By.id("users-table");

    // Form locators for create/edit
    private By nameInput = By.id("username");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By roleSelect = By.id("role");
    private By submitButton = By.id("save-user-btn");

    // For edit/delete actions on specific user
    private By editButton(String userName) {
        return By.xpath("//tr[contains(.,'" + userName + "')]//button[contains(@class,'edit-user-btn')]");
    }

    private By deleteButton(String userName) {
        return By.xpath("//tr[contains(.,'" + userName + "')]//button[contains(@class,'delete-user-btn')]");
    }

    private By confirmDeleteButton = By.id("confirm-delete");

    // 🔹 Validation
    @Step("Validate that Users page is loaded")
    public boolean isLoaded() {
        return isVisible(usersPageIdentifier);
    }

    @Step("Wait until Users page is fully loaded")
    public void waitUntilLoaded() {
        find(usersPageIdentifier);
    }

    // 🔹 Private Actions
    @Step("Click Create User button")
    private void clickCreateUser() {
        click(createUserButton);
    }

    @Step("Fill user form with name: {name}, email: {email}, password: {password}, role: {role}")
    private void fillUserForm(String name, String email, String password, String role) {
        type(nameInput, name);
        type(emailInput, email);
        type(passwordInput, password);
        selectRole(role);
    }

    @Step("Select role: {role}")
    private void selectRole(String role) {
        String roleValue = getRoleValue(role);
        selectByValue(roleSelect, roleValue);
        clickBody();
    }

    private String getRoleValue(String role) {
        switch (role) {
            case "Cajero":
                return "tiendo_cajero";
            case "Tendero (Administrador)":
                return "tiendo_tendero";
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    @Step("Click Submit button")
    private void clickSubmit() {
        click(submitButton);
    }

    @Step("Click Edit button for user: {userName}")
    private void clickEditUser(String userName) {
        click(editButton(userName));
    }

    @Step("Click Delete button for user: {userName}")
    private void clickDeleteUser(String userName) {
        click(deleteButton(userName));
    }

    @Step("Confirm delete action")
    private void confirmDelete() {
        click(confirmDeleteButton);
    }

    // 🔹 Business Actions
    @Step("Create a new user with name: {name}, email: {email}, password: {password}, role: {role}")
    public void createUser(String name, String email, String password, String role) {
        clickCreateUser();
        fillUserForm(name, email, password, role);
        clickSubmit();
        System.out.println(name + " " + email + " " + password + " " + role);
        // Assuming after submit, the form closes or page refreshes
        waitUntilLoaded();
    }

    @Step("Edit user {oldName} to new details: name: {newName}, email: {newEmail}, role: {newRole}")
    public void editUser(String oldName, String newName, String newEmail, String newRole) {
        clickEditUser(oldName);
        fillUserForm(newName, newEmail, "", newRole);
        clickSubmit();
        System.out.println(newName + " " + newEmail + " " + newRole);
        waitUntilLoaded();
    }

    @Step("Delete user: {userName}")
    public void deleteUser(String userName) {
        clickDeleteUser(userName);
        confirmDelete();
        waitUntilLoaded();
    }

    // 🔹 Validations
    @Step("Verify user {userName} is present in the list")
    public boolean isUserPresent(String userName) {
        return isVisible(By.xpath("//tr[td[text()='" + userName + "']]"));
    }

    @Step("Verify user {userName} is not present in the list")
    public boolean isUserNotPresent(String userName) {
        return !isVisible(By.xpath("//tr[td[text()='" + userName + "']]"));
    }
}