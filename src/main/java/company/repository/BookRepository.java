package company.repository;

import company.model.Book;

import java.util.Collection;

/**
 * Created by Alexander on 04.05.2017.
 */
public interface BookRepository {

    Book save(Book book);

    boolean delete(int id);

    Book get(int id);

    Collection<Book> getAll();

}
