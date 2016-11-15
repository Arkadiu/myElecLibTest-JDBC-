import com.yourcomapany.Book;
import com.yourcomapany.DBWorker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Alexander on 11.09.2016.
 */
@javax.servlet.annotation.WebServlet(name = "Servlet", urlPatterns = "/Servlet")
public class Servlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();


        DBWorker dbWorker = new DBWorker();
        List<Book> list = dbWorker.showAllRow();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        for (Book book : list) {
            array.put(book);
        }
        result.put("books", array);

        writer.println(result);
        writer.flush();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
