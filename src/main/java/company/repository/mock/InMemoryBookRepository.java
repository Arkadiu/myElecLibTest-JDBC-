package company.repository.mock;

import company.model.Book;
import company.repository.BookRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexander on 04.05.2017.
 */
public class InMemoryBookRepository implements BookRepository {

    private Map<Integer, Book> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Book save(Book book) {
        if (book.isNew()) {
            book.setId(counter.getAndIncrement());
        }
        repository.put(book.getId(), book);
        return book;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Book get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Book> getAll() {
        return repository.values();
    }
}
