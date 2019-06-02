package Servlets;

import Bean.DatabaseChecker;
import Domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {


    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
            throws ServletException, IOException {

//         this forces the URL to become signup.jsp, something to avoid!
//        String signup = "signup.jsp";
//        res.sendRedirect(signup);
//
//
        DatabaseChecker dbChecker = new DatabaseChecker();
        User user;
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String dob = req.getParameter("dob");
        String homeAddress = req.getParameter("homeAddress");
        String BIC = req.getParameter("BIC");

        //1 indicates this user is a voter. 0 for Election Commision users.
        user = new User(1,email,fullName,dob,password,homeAddress,BIC);




        //To check if users email and date of birth are valid!
        Pattern emailP = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailM = emailP.matcher(email);

        Pattern dateP = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
        Matcher dateM = dateP.matcher(dob);


        if (dateM.find()) {

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            String[] dobYear = dob.split("-");

            if (currentYear - Integer.parseInt(dobYear[0]) >= 16) {

                if (dbChecker.isBICAvailable(user.getBIC())) {

                    if (!(dbChecker.existingEmail(email))) {

                        if (emailM.find()) {
                            try {
                                dbChecker.signup(user);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                res.sendRedirect("../login.jsp");
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }

                        } else {
                            res.sendRedirect("../error.jsp?id=2");
                        }
                    } else {
                        res.sendRedirect("../error.jsp?id=2");
                    }
                } else {
                    res.sendRedirect("../error.jsp?id=3");
                }
            } else {
                res.sendRedirect("../error.jsp?id=4");
            }
        }else{
            res.sendRedirect("../error.jsp?id=4");
        }
//        this dispatcher passes signup.jsp as the request and therefore web.xml matches this to /register in url!

    }
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}

