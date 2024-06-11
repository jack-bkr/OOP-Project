package mechanicStock.Classes;

public class User {
    private int userID;
    private String userName;
    private String userPassword;
    private boolean isAdmin;
    private String dateRegistered;

    public User(int userID, String userName, String userPassword, boolean isAdmin, String dateRegistered) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
        this.dateRegistered = dateRegistered;
    }

    public String toString() {
        return "User ID: " + userID + "\nUsername: " + userName + "\nPassword: " + userPassword + "\nIs Admin: " + isAdmin
                + "\nDate Registered: " + dateRegistered;
    }
    
    public boolean checkPassword(String password) {
        return userPassword.equals(password);
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
