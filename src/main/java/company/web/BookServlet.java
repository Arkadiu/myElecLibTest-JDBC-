package company.web;

import company.dao.BookDao;
import company.model.Book;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Alexander on 11.09.2016.
 */
public class BookServlet extends HttpServlet {

    private static final Logger LOG = getLogger(BookServlet.class);
    private BookDao bookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = new BookDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                bookDao.deleteBook(id);
                response.sendRedirect("books");
                break;
            case "create":
            case "update":
                final Book book = action.equals("create") ?
                        new Book("Новая книга", "Автор", "Описание") :
                        bookDao.getBookById(getId(request));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/book.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("books", bookDao.getAllBooks());
                request.getRequestDispatcher("/books.jsp").forward(request, response);
                break;
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LOG.debug("doPost");

        Book book = new Book(
                //id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("book_name"),
                request.getParameter("author"),
                request.getParameter("description")
        );

        if (id == null || id.isEmpty()){
            bookDao.addBook(book);
        }else
        {
            book.setId(Integer.parseInt(id));
            bookDao.updateBook(book);
        }
        //response.sendRedirect("books");
        RequestDispatcher view = request.getRequestDispatcher("/books.jsp");
        request.setAttribute("books", bookDao.getAllBooks());
        view.forward(request, response);
    }


}
