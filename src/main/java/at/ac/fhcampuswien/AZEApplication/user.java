package at.ac.fhcampuswien.AZEApplication;

/**
 * This class is responsible for getting and setting the username.
 * to be able to later use in database query to get the right user's data.
 */
public class user {
    private static String username;

    public user(String username) {
        this.username = username;
    }

    /**
     * Allows to get the user's name.
     * @return the actual username.
     */
    public static String getUsername(){
        return username;
    }

    /**
     * Allows to set the users name.
     * @param newName to change the username with this value.
     */
    public static void changeName(String newName) {
        username = newName;
    }
}
