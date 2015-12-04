package servlet;

import com.mysql.jdbc.Statement;
import javaBeans.Mrecords;
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
                    regToDB(request,response,user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "search":{
                User user = new User();
                String searchName = request.getParameter("search-name");
                user.setName(searchName);
                user.setRole("patients");
                searchPatient(request,response,user);

                break;
            }
            case "show":{
                User user =new User();
                user.setId(request.getParameter("pid"));
                showPatient(request,response,user);

                break;
            }
            case "modify":{
                Mrecords mrecords = new Mrecords();
                mrecords.setP_id(request.getParameter("p_id"));
                mrecords.setM_cc(request.getParameter("m_cc"));
                mrecords.setM_curh(request.getParameter("m_curh"));
                mrecords.setM_lsth(request.getParameter("m_lsth"));
                mrecords.setM_perh(request.getParameter("m_perh"));
                mrecords.setM_famh(request.getParameter("m_famh"));

                try {
                    modifyMR(request,response,mrecords);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);

    }

    public void modifyMR(HttpServletRequest request, HttpServletResponse response,Mrecords mrecords) throws SQLException, ServletException, IOException {
        String sql = "UPDATE mrecords SET m_cc='"+ mrecords.getM_cc() +"',m_curh='"+ mrecords.getM_curh() +"',m_lsth='"+ mrecords.getM_lsth() +"',m_perh='"+ mrecords.getM_perh() +"',m_famh='"+ mrecords.getM_famh() +"' WHERE p_id='"+ mrecords.getP_id() +"'";
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;
            stmt.execute(sql);

            sql = "select * from mrecords";
            rs = stmt.executeQuery(sql);
            if (!rs.next()){
                System.out.println("Error: can't find this records");
                return;
            }

            System.out.println("cc:"+rs.getString(2));
            System.out.println("cur:"+rs.getString(3));
            System.out.println("lst:"+rs.getString(4));
            System.out.println("per:"+rs.getString(5));
            System.out.println("fam:"+rs.getString(6));
            System.out.println("pid:"+rs.getString(7));
            rs.close();
            request.getRequestDispatcher("/modify_patient_successfully.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            stmt.close();
        }
    }

    public void showPatient(HttpServletRequest request, HttpServletResponse response,User user) throws ServletException, IOException {
        Mrecords mr = new Mrecords();
        mr.setP_id(user.getId());
        String sql = "SELECT p_name,p_age,p_gender,m_cc,m_curh,m_lsth,m_perh,m_famh FROM patients,mrecords WHERE patients.p_id='"+ user.getId() +"' AND mrecords.p_id='"+ user.getId() +"'";
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                user.setName(rs.getString(1));
                user.setAge(rs.getInt(2));
                user.setGender(rs.getString(3));
                mr.setM_cc(rs.getString(4));
                mr.setM_curh(rs.getString(5));
                mr.setM_lsth(rs.getString(6));
                mr.setM_perh(rs.getString(7));
                mr.setM_famh(rs.getString(8));
            }

            request.setAttribute("p_id",user.getId());
            request.setAttribute("p_name",user.getName());
            request.setAttribute("p_age",user.getAge());
            request.setAttribute("p_gender",user.getGender());
            request.setAttribute("m_cc",mr.getM_cc());
            request.setAttribute("m_curh",mr.getM_curh());
            request.setAttribute("m_lsth",mr.getM_lsth());
            request.setAttribute("m_perh",mr.getM_perh());
            request.setAttribute("m_famh",mr.getM_famh());

            request.getRequestDispatcher("/patient_details_d.jsp").forward(request,response);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchPatient(HttpServletRequest request, HttpServletResponse response,User user) throws IOException, ServletException {
        String sql = "SELECT * FROM patients WHERE p_name='"+user.getName()+"'";
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            if (!rs.next()){
                System.out.println("No Results");
                response.sendRedirect(request.getContextPath() + "/login_failed.jsp");
            }
            user.setId(rs.getString(1));
            user.setAge(rs.getInt(3));
            user.setGender(rs.getString(4));
            request.setAttribute("p_id",user.getId());
            request.setAttribute("p_name",user.getName());
            request.setAttribute("p_age",user.getAge());
            request.setAttribute("p_gender",user.getGender());

            request.getRequestDispatcher("/search_results.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regToDB(HttpServletRequest request, HttpServletResponse response,User user) throws SQLException, IOException {
        int flag = 0;
        String r_id = null;
        try {
            if (user.getRole().equals("doctors")){
                pstate = conn.prepareStatement("INSERT INTO doctors(d_id, d_name, d_age, d_gender) VALUES (?,?,?,?)");
                r_id = "d"+UUID.randomUUID().toString();
                pstate.setString(1,r_id);
                pstate.setString(2,user.getName());
                pstate.setInt(3,user.getAge());
                pstate.setString(4,user.getGender());
                flag = pstate.executeUpdate();

            }
            if (user.getRole().equals("patients")){
                pstate = conn.prepareStatement("INSERT INTO patients(p_id, p_name, p_age, p_gender) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                r_id = "p"+UUID.randomUUID().toString();
                pstate.setString(1,r_id);
                pstate.setString(2,user.getName());
                pstate.setInt(3,user.getAge());
                pstate.setString(4,user.getGender());
                flag=pstate.executeUpdate();


                pstate = conn.prepareStatement("INSERT INTO mrecords(p_id) VALUES (?)");
                pstate.setString(1,r_id);
                pstate.executeUpdate();

            }
            pstate = conn.prepareStatement("INSERT INTO users(u_name, u_password, r_id) VALUES (?,?,?)");
            pstate.setString(1,user.getUsername());
            pstate.setString(2,user.getPassword());
            pstate.setString(3,r_id);
            System.out.println("r_id:"+r_id);
            pstate.executeUpdate();
            response.sendRedirect(request.getContextPath()+"/register_successfully.jsp");
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            pstate.close();
//            conn.close();
        }

    }
    public void logToDB(HttpServletRequest request, HttpServletResponse response,User user) throws IOException, ServletException {
        try {
            stmt = conn.createStatement();
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
                Mrecords mrecords = new Mrecords();
                String p_id = null;
                rs = stmt.executeQuery("SELECT p_name,p_age,p_gender,p_id FROM patients WHERE p_id=(SELECT r_id FROM users WHERE r_id LIKE 'p%' AND u_name='" + user.getUsername() + "' AND u_password='" + user.getPassword() + "')");
                if (!rs.next()) {
                    System.out.println("Login Failed");
                    response.sendRedirect(request.getContextPath() + "/login_failed.jsp");
                    return;
                }
                user.setName(rs.getString(1));
                user.setAge(rs.getInt(2));
                user.setGender(rs.getString(3));
                user.setId(rs.getString(4));

                request.setAttribute("p_name",user.getName());
                request.setAttribute("p_age",user.getAge());
                request.setAttribute("p_gender",user.getGender());

                rs = stmt.executeQuery("SELECT m_cc,m_curh,m_lsth,m_perh,m_famh FROM mrecords WHERE p_id='"+ user.getId() +"'");
                if (rs.next()) {
                    mrecords.setP_id(user.getId());
                    mrecords.setM_cc(rs.getString(1));
                    mrecords.setM_curh(rs.getString(2));
                    mrecords.setM_lsth(rs.getString(3));
                    mrecords.setM_perh(rs.getString(4));
                    mrecords.setM_famh(rs.getString(5));
                }
                System.out.println("p_id:"+mrecords.getP_id());
                System.out.println("m_cc:"+mrecords.getM_cc());
                System.out.println("m_curh:"+mrecords.getM_curh());
                System.out.println("m_lsth:"+mrecords.getM_lsth());
                System.out.println("m_perh:"+mrecords.getM_perh());
                System.out.println("m_famh:"+mrecords.getM_famh());

                request.setAttribute("p_name", user.getName());
                request.setAttribute("p_age",user.getAge());
                request.setAttribute("p_gender",user.getGender());
                request.setAttribute("m_cc",mrecords.getM_cc());
                request.setAttribute("m_curh",mrecords.getM_curh());
                request.setAttribute("m_lsth",mrecords.getM_lsth());
                request.setAttribute("m_perh",mrecords.getM_perh());
                request.setAttribute("m_famh",mrecords.getM_famh());

                request.getRequestDispatcher("/patient_details_p.jsp").forward(request,response);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            try{
//                if(stmt!=null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
        }
    }



}
