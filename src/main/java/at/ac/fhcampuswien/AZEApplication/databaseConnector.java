package at.ac.fhcampuswien.AZEApplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnector {
    public Connection databaseLink;

    /**
     * gets the connection to the database.
     * @return the database link.
     */
    public Connection getConnection(){
        String databaseName = "aze_db";
        String databaseUser = "root";
        String databasePassword = "Mysql@4311";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
