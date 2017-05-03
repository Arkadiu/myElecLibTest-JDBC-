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

    private static final String DELETE = "DELETE FROM library WHERE id = ?";
    private static final String INSERT = "INSERT INTO library VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE library SET book_name=?, author=?, description=? WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM library";
    private static final String GET_BY_ID = "SELECT * FROM library WHERE id=?";

    private static final String SEARCH_REQUEST_BOOK = "SELECT * FROM library WHERE book_name = ?";
    private static final String SEARCH_REQUEST_AUTHOR = "SELECT * FROM library WHERE author = ?";

    public DBWorker() {
        connection = DbUtil.getConnection();
    }

    public void addBook(int id, String name, String author, String desc, String icon) {
        if (icon.equals("") || icon == null)
            icon = "image.jpg";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

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
    }

    public void updateBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDesc());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet res = preparedStatement.executeQuery(GET_ALL);
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("book_name");
                String author = res.getString("author");
                String description = res.getString("description");
                byte[] icon = res.getBytes("icon");
                Book book = new Book(id, name, author, description, icon);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getBookById(int bookId){
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, bookId);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                int id = res.getInt("id");
                String name = res.getString("book_name");
                String author = res.getString("author");
                String description = res.getString("description");
                byte[] icon = res.getBytes("icon");
                book = new Book(id, name, author, description, icon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
