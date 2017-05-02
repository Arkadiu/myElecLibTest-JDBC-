package company.util;

import company.model.Book;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Alexander on 02.05.2017.
 */
public class BookUtil {
    public static final List<Book> LIST = asList(
            new Book(1, "Проверка", "Автор", "Просто проверка"),
            new Book(2, "Проверка", "Автор", "Просто проверка")
    );
}
