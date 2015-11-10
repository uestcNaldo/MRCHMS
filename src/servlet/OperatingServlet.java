package servlet;

import javaBeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 11656 on 2015/10/24.
 */
public class OperatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getParameter("action")){
            case "login":{
                if (request.getParameter("ID").equals("doctor")) {
                    System.out.println("ID:"+request.getParameter("ID"));
                    //Detect ID is Doctor
                }
                if (request.getParameter("ID").equals("patient")){
                    System.out.println("ID:"+request.getParameter("ID"));
                    //Detect ID is patient
                }

                break;
            }
            case "register":{
                System.out.println("Action:"+request.getParameter("action"));
                response.sendRedirect(request.getContextPath()+"/register.jsp");

                break;
            }
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request,response);

    }


}
