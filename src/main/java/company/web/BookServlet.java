package company.web;

import company.dao.DBWorker;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Alexander on 11.09.2016.
 */
public class BookServlet extends HttpServlet {

    private static final Logger LOG = getLogger(BookServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("getAll");
        DBWorker dbWorker = new DBWorker();
        request.setAttribute("books", dbWorker.getAllBooks());
        getServletContext().getRequestDispatcher("/books.jsp").forward(request, response);
        System.out.println(new DBWorker().getAllBooks());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}