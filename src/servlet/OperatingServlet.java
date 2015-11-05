package servlet;

import beans.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 * Created by 11656 on 2015/10/24.
 */
public class OperatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

}
