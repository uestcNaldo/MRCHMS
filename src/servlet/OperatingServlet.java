package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 * Created by 11656 on 2015/10/24.
 */
public class OperatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

}
