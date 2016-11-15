package com.yourcomapany;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 10.09.2016.
 */
public class DBWorker {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Driver driver;
    private int count = 0;

    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL = "SELECT * FROM lib";
    private static final String DELETE = "DELETE FROM lib WHERE id = ?";
    private static final String INSERT_NEW = "INSERT INTO lib VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_INFO = "UPDATE lib SET description = ? WHERE id = ?";
    private static final String SEARCH_REQUEST_BOOK = "SELECT * FROM lib WHERE book_name = ?";
    private static final String SEARCH_REQUEST_AUTHOR = "SELECT * FROM lib WHERE author = ?";

    public DBWorker() {

        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> showAllRow() {
        List<Book> list = new ArrayList<>();
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet res = preparedStatement.executeQuery();
            list.addAll(printTable(res));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteBookById(int id) {
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllRow();
    }

    public void insertNewBook(int id, String name, String author, String desc, String icon) {
        if (icon.equals(""))
            icon = "image.jpg";
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, desc);
            preparedStatement.setBlob(5, new FileInputStream(icon));
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Изображение не найдено");
        }

        showAllRow();
    }

    public void updateBookDescription(int id, String desc) {
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INFO);
            preparedStatement.setString(1, desc);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllRow();
    }

    public List<Book> searchBookByNameOrAuthor(String searchString) {
        List<Book> list = new ArrayList<>();
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(SEARCH_REQUEST_BOOK);
            preparedStatement.setString(1, searchString);
            ResultSet res = preparedStatement.executeQuery();

            if (!res.next()) {
                preparedStatement = connection.prepareStatement(SEARCH_REQUEST_AUTHOR);
                preparedStatement.setString(1, searchString);
                res = preparedStatement.executeQuery();
            }
            list.addAll(printTable(res));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Book> printTable(ResultSet res) throws SQLException {
        List<Book> list = new ArrayList<>();
        System.out.println("Запрос в текущей сессии номер №" + ++count);
        System.out.println("╔═══════╦════════════╦═══════════╦══════════════════╗");
        while (res.next()) {
            Book book = new Book();
            book.setId(res.getInt("id"));
            book.setName(res.getString("book_name"));
            book.setAuthor(res.getString("author"));
            book.setDesc(res.getString("description"));
            book.setIcon(res.getBytes("icon"));
            System.out.println(book);
        }
        System.out.println("╚═══════╩════════════╩═══════════╩══════════════════╝");
        return list;
    }

    private void openConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
