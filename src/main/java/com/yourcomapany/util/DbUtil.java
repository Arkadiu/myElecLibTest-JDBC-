package com.yourcomapany.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Alexander on 28.04.2017.
 */
public class DbUtil {

    public static Connection getConnection() {
        Connection connection = null;

        if (connection != null) {
            return connection;
        } else {
            try (InputStream inputStream = DbUtil.class.getResourceAsStream("/mysql.properties")) {
                //load properties file
                Properties prop = new Properties();
                prop.load(inputStream);

                //sign db parameters
                String url = prop.getProperty("url");
                String user = prop.getProperty("username");
                String password = prop.getProperty("password");
                String driver = prop.getProperty("driver");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
