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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by 11656 on 2015/10/24.
 */
public class OperatingServlet extends HttpServlet {
    private Connection conn = null;
    private PreparedStatement pstate = null;
    private java.sql.Statement stmt = null;
    public void init() {
        try {
            conn = DBUtil.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                    logToDB(request,response,user);
                }
                if (request.getParameter("ID").equals("patient")){
                    System.out.println("ID:"+request.getParameter("ID"));
                    //Detect ID is patient
                    user.setRole("patients");
                    logToDB(request,response,user);
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
                try {
                    regToDB(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "search":{
                User user = new User();
                String searchName = request.getParameter("search-name");
                user.setName(searchName);

                break;
            }
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);

    }

    public void regToDB(User user) throws SQLException {
        int flag = 0;
        String r_id = null;
        ResultSet rs = null;
        try {
            if (user.getRole().equals("doctors")){
                pstate = conn.prepareStatement("INSERT INTO doctors(d_id, d_name, d_age, d_gender) VALUES (?,?,?,?)");
                pstate.setString(1, "d"+UUID.randomUUID().toString());
                pstate.setString(2,user.getName());
                pstate.setInt(3,user.getAge());
                pstate.setString(4,user.getGender());
                flag = pstate.executeUpdate();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM doctors");
                if (rs.next()){
                    rs.last();
                    r_id = rs.getString(1);
                }
            }
            if (user.getRole().equals("patients")){
                pstate = conn.prepareStatement("INSERT INTO patients(p_id, p_name, p_age, p_gender) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                pstate.setString(1,"p"+UUID.randomUUID().toString());
                pstate.setString(2,user.getName());
                pstate.setInt(3,user.getAge());
                pstate.setString(4,user.getGender());
                flag=pstate.executeUpdate();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM patients");
                if (rs.next()){
                    rs.last();
                    r_id = rs.getString(1);
                }

            }
            pstate = conn.prepareStatement("INSERT INTO users(u_name, u_password, r_id) VALUES (?,?,?)");
            pstate.setString(1,user.getUsername());
            pstate.setString(2,user.getPassword());
            pstate.setString(3,r_id);
            System.out.println("r_id:"+r_id);
            pstate.executeUpdate();

            pstate = conn.prepareStatement("INSERT INTO mrecords(p_id) VALUES ?");
            pstate.setString(1,r_id);
            pstate.executeUpdate();

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pstate.close();
            conn.close();
        }

    }
    public void logToDB(HttpServletRequest request, HttpServletResponse response,User user) throws IOException, ServletException {
        try {
            stmt = (Statement) conn.createStatement();
            ResultSet rs = null;
            if (user.getRole().equals("doctors")) {
                rs = stmt.executeQuery("SELECT d_name,d_age,d_gender FROM doctors WHERE d_id=(SELECT r_id FROM users WHERE r_id LIKE 'd%' AND u_name='"+user.getUsername()+"' AND u_password='"+user.getPassword()+"')");
                if (!rs.next()) {
                    System.out.println("Login Failed");
                    response.sendRedirect(request.getContextPath() + "/login_failed.jsp");
                    return;
                }
                user.setName(rs.getString(1));
                user.setAge(rs.getInt(2));
                user.setGender(rs.getString(3));

                System.out.println("Name:"+user.getName());
                System.out.println("Age:"+user.getAge());
                System.out.println("Gender:"+user.getGender());

                request.setAttribute("d_name",user.getName());
                request.setAttribute("d_age",user.getAge());
                request.setAttribute("d_gender",user.getGender());
                request.getRequestDispatcher("/doctor_detail.jsp").forward(request,response);
            }
            if (user.getRole().equals("patients")){
                String p_id = null;
                rs = stmt.executeQuery("SELECT p_name,p_age,p_gender,p_id FROM patients WHERE p_id=(SELECT r_id FROM users WHERE r_id LIKE 'p%' AND u_name='" + user.getUsername() + "' AND u_password='" + user.getPassword() + "')");
                if (!rs.next()) {
                    System.out.println("Login Failed");
                    response.sendRedirect(request.getContextPath() + "/login_failed.jsp");
                }
                user.setName(rs.getString(1));
                user.setAge(rs.getInt(2));
                user.setGender(rs.getString(3));
                p_id = rs.getString(4);

                request.setAttribute("p_name",user.getName());
                request.setAttribute("p_age",user.getAge());
                request.setAttribute("p_gender",user.getGender());
                rs = stmt.executeQuery("SELECT m_cc,m_curh,m_lsth,m_perh,m_famh FROM mrecords WHERE p_id='"+ p_id +"'");


            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }



}
