package at.ac.fhcampuswien.AZEApplication;

public class user {
    private static String username;

    public user(String username) {
        this.username = username;
    }

    public static String getUsername(){
        return username;
    }

    public static void changeName(String newName) {
        username = newName;
    }
}
