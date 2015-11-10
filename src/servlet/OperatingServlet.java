package servlet;

import com.mysql.jdbc.Statement;
import javaBeans.User;
import utils.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 11656 on 2015/10/24.
 */
public class OperatingServlet extends HttpServlet {
    private Connection conn = DBUtil.conn;
    private Statement state = null;
    private PreparedStatement pstate = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getParameter("action")){
            case "login":{
                User user = new User();
                String username = request.getParameter("login-username");
                String password = request.getParameter("login-password");
                user.setUsername(username);
                user.setPassword(password);
                if (request.getParameter("ID").equals("doctor")) {
                    System.out.println("ID:"+request.getParameter("ID"));
                    //Detect ID is Doctor
                    user.setRole("doctors");
                }
                if (request.getParameter("ID").equals("patient")){
                    System.out.println("ID:"+request.getParameter("ID"));
                    //Detect ID is patient
                    user.setRole("patients");

                }
                break;
            }
            case "toRegister":{
                System.out.println("Action:"+request.getParameter("action"));
                response.sendRedirect(request.getContextPath()+"/register.jsp");
                break;
            }
            case "register":{
                System.out.println("Action:"+request.getParameter("action"));
                User user = new User();
                String role = request.getParameter("role");
                System.out.println("role:"+role);
                String username = request.getParameter("register-username");
                System.out.println("username:"+username);
                String password = request.getParameter("register-password");
                String name = request.getParameter("register-name");
                int age = Integer.parseInt(request.getParameter("register-age"));
                String gender = request.getParameter("sex");
                user.setRole(role);
                user.setUsername(username);
                user.setPassword(password);
                user.setName(name);
                user.setAge(age);
                user.setGender(gender);

            }
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);

    }

    public void regToDB(User user){
        try {
            pstate = conn.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
