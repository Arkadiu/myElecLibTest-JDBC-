package company.dao;

import company.model.Book;
import company.repository.BookRepository;
import company.repository.mock.InMemoryBookRepository;
import company.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 10.09.2016.
 */
public class BookDao {

    private Connection connection = null;

    private static final String DELETE = "DELETE FROM library WHERE id = ?";
    private static final String INSERT = "INSERT INTO library VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE library SET book_name=?, author=?, description=? WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM library";
    private static final String GET_BY_ID = "SELECT * FROM library WHERE id=?";

    /*
    private static final String SEARCH_REQUEST_BOOK = "SELECT * FROM library WHERE book_name = ?";
    private static final String SEARCH_REQUEST_AUTHOR = "SELECT * FROM library WHERE author = ?";
    */

    private BookRepository repository = new InMemoryBookRepository();

    public BookDao() {
        connection = DbUtil.getConnection();
    }

    public void addBook(Book bookForMemory) {
        /*
        if (book.getIcon() == null || book.getIcon().equals(""))
            icon = "image.jpg";
            */

        Book book = repository.save(bookForMemory);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getDesc());
            //preparedStatement.setBlob(5, new FileInputStream(icon));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } /*
        catch (FileNotFoundException e) {
            System.out.println("Изображение не найдено");
        }
        */
    }

    public void updateBook(Book bookForMemory) {
        Book book = repository.save(bookForMemory);
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
        repository.delete(id);
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
