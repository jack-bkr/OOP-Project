package mechanicStock.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;


class UserTest {
    LocalDateTime dateTime = LocalDateTime.of(2022, 12, 31, 23, 59, 59); // Specific date and time
    Timestamp timestamp = Timestamp.valueOf(dateTime); // Convert to Timestamp
    private User user = new User(1, "username", "password", false, timestamp, true);
    private ArrayList<User> users = User.getAllUsers();

    @Test
    void testGetUserID() {
        assertEquals(1, user.getUserID());
    }

    @Test
    void testGetUserName() {
        assertEquals("username", user.getUserName());
    }

    @Test
    void testGetUserPassword() {
        assertEquals("password", user.getUserPassword());
    }

    @Test
    void testGetIsAdmin() {
        assertFalse(user.getIsAdmin());
    }

    @Test
    void testGetDateRegistered() {
        assertEquals(timestamp, user.getDateRegistered());
    }

    @Test
    void testGetAdminApproved() {
        assertTrue(user.getAdminApproved());
    }

    @Test
    void testCheckPassword() {
        assertTrue(user.checkPassword("password"));
        assertFalse(user.checkPassword("wrongpassword"));
    }

    @Test
    void testRegisterUser() {
        assertTrue(User.registerUser("testUser", "newPassword"));
        User.getUserByUserName("testUser").deleteItem();
    }

    @Test
    void testGetUserByUserName() {
        User testUser = User.getUserByUserName("Jack");
        assertEquals("Jack", testUser.getUserName());
        assertEquals("asdf", testUser.getUserPassword());
    }

    @Test
    void testGetUserByID() {
        User testUser = User.getUserByID(1);
        assertEquals("Jack", testUser.getUserName());
        assertEquals("asdf", testUser.getUserPassword());
    }

    @Test
    void testApproveUser() {
        User testUser = User.getUserByUserName("Jack");
        assertTrue(testUser.approveUser());
    }

    @Test
    void testToggleAdmin() {
        User testUser = User.getUserByUserName("Jack");
        assertTrue(testUser.toggleAdmin());
        if (!testUser.getIsAdmin()) {
            testUser.toggleAdmin();
        }
    }

    @Test
    void testDeleteItem() {
        User.registerUser("testUser", "pass");
        User testUser = User.getUserByUserName("testUser");
        assertTrue(testUser.deleteItem());
    }

    @Test
    void testGetAllUsers() {
        ArrayList<User> testUsers = User.getAllUsers();
        assertEquals(this.users.size(), testUsers.size());
    }

}