package Servlets;

import Bean.DatabaseChecker;
import Bean.HashGenerator;
import Domain.McQuestion;
import Domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.http.Cookie;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet{

    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
            throws ServletException, IOException {


        User user;



        String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");





        DatabaseChecker dbc = new DatabaseChecker();

        try {

            user = dbc.validateUser(userEmail, userPassword);



            if (userEmail.isEmpty() || userPassword.isEmpty()){

                req.setAttribute("errorMessage","Please fill both fields.");
                RequestDispatcher rd = req.getRequestDispatcher("/");
                rd.forward(req, res);
            }else if (user != null){

                HttpSession se = req.getSession();
                se.setAttribute("user", user);
                System.out.println("mpik"+ userEmail);

                Cookie usernameCookie = new Cookie("userEmail",userEmail);
                usernameCookie.setMaxAge(72000);
                usernameCookie.setPath("/");
                res.addCookie(usernameCookie);

                boolean percentage ;
               if (user.getRole() == 0){

                    percentage = dbc.registeredCheck();
                    se.setAttribute("percentage",percentage);
                    res.sendRedirect("../electionCom.jsp");
                }else{

                    res.sendRedirect("../mainVoter.jsp");
                }
            }else{
                res.sendRedirect("../error.jsp?id=0");
            }

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);}
}
