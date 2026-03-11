package tests.regression.authentication;

import base.BaseTest;
import driver.DriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.UsersPage;

@Epic("User Management")
@Feature("Administrator User Operations")
@Owner("Salem Amortegui")
public class UserManagementTest extends BaseTest {

    @Test(groups = {"regression", "user-management"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test the complete flow of creating, editing, and deleting a user by an administrator")
    @Story("RF-04 - As an administrator, I want to manage users so that I can add, modify, and remove user accounts")
    public void testUserCRUDOperations() {
        // Login as administrator
        DashboardPage dashboardPage = doLogin(email, password);

        // Navigate to Users page
        UsersPage usersPage = dashboardPage.goToUsers();
        Assert.assertTrue(usersPage.isLoaded());

        // Generate unique test data to avoid conflicts
        String uniqueId = String.valueOf(System.currentTimeMillis());
        String userName = "TestUser" + uniqueId;
        String userEmail = "testuser" + uniqueId + "@example.com";
        String userPassword = "password123";
        String userRole = "Cajero"; // Assuming roles like Cajero, Tendero (Administrador)

        // Create a new user
        usersPage.createUser(userName, userEmail, userPassword, userRole);

        // Verify user is created
        Assert.assertTrue(usersPage.isUserPresent(userName), "User should be present after creation");

        // Edit the user
        String newUserName = "EditedUser" + uniqueId;
        String newUserEmail = "editeduser" + uniqueId + "@example.com";
        String newUserRole = "Tendero (Administrador)";
        usersPage.editUser(userName, newUserName, newUserEmail, newUserRole);

        // Verify user is edited
        Assert.assertTrue(usersPage.isUserPresent(newUserName), "Edited user should be present");
        Assert.assertTrue(usersPage.isUserNotPresent(userName), "Original user should not be present after edit");

        // Delete the user
        usersPage.deleteUser(newUserName);

        // Verify user is deleted
        Assert.assertTrue(usersPage.isUserNotPresent(newUserName), "User should not be present after deletion");
    }
}