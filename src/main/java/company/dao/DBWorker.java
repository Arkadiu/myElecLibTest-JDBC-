package company.dao;

import company.model.Book;
import company.util.DbUtil;

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
    private int countRequest = 0;

    private static final String GET_ALL = "SELECT * FROM library";
    private static final String DELETE = "DELETE FROM library WHERE id = ?";
    private static final String INSERT_NEW = "INSERT INTO library VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_INFO = "UPDATE library SET description = ? WHERE id = ?";
    private static final String SEARCH_REQUEST_BOOK = "SELECT * FROM library WHERE book_name = ?";
    private static final String SEARCH_REQUEST_AUTHOR = "SELECT * FROM library WHERE author = ?";

    public DBWorker() {
        connection = DbUtil.getConnection();
    }

    public void addBook(int id, String name, String author, String desc, String icon) {
        if (icon.equals("") || icon == null)
            icon = "image.jpg";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, desc);
            preparedStatement.setBlob(5, new FileInputStream(icon));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Изображение не найдено");
        }
        showAllRow();
    }

    public void deleteBook(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllRow();
    }

    public void updateBookDescription(int id, String desc) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INFO);

            preparedStatement.setString(1, desc);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllRow();
    }

    public List<Book> searchBookByNameOrAuthor(String searchString) {
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_REQUEST_BOOK);

            preparedStatement.setString(1, searchString);
            ResultSet res = preparedStatement.executeQuery();

            if (!res.next()) {
                preparedStatement = connection.prepareStatement(SEARCH_REQUEST_AUTHOR);
                preparedStatement.setString(1, searchString);
                res = preparedStatement.executeQuery();
            }
            list.addAll(printTable(res));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> showAllRow() {
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);

            ResultSet res = preparedStatement.executeQuery();
            list.addAll(printTable(res));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private List<Book> printTable(ResultSet res) throws SQLException {
        List<Book> list = new ArrayList<>();
        System.out.println("Запрос в текущей сессии номер №" + ++countRequest);
        System.out.println(
                "╔══════════════════╦═══════════════════════════╦══════════════════════╦════════════════════════════════════╗");
        while (res.next()) {
            Book book = new Book();
            book.setId(res.getInt("id"));
            book.setName(res.getString("book_name"));
            book.setAuthor(res.getString("author"));
            book.setDesc(res.getString("description"));
            book.setIcon(res.getBytes("icon"));
            System.out.println(book);
        }
        System.out.println(
                "╚══════════════════╩═══════════════════════════╩══════════════════════╩════════════════════════════════════╝");
        return list;
    }

    /*private void openConnection() throws SQLException {
        if (connection == null) {
            connection = new DbUtil().getConnection();
        }
    }*/

  /*  private void closeConnection() throws SQLException {
        connection.close();
    }*/
}
