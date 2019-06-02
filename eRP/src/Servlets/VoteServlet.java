package Servlets;

import Bean.DatabaseChecker;
import Domain.McQuestion;
import Domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class VoteServlet extends HttpServlet {



    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        HttpSession session = req.getSession();

        DatabaseChecker dbc = new DatabaseChecker();

        McQuestion refQuestion = dbc.getMcQuestion();

        User voter;


        voter = (User)session.getAttribute("user");


        if (voter.getRole() == 1) {
            if (!voter.getHasVoted()) {
                session.setAttribute("refQuestion", refQuestion);
                res.sendRedirect("../referendum.jsp");
            } else {

                res.sendRedirect("../error.jsp?id=5");
            }
        }else{
            res.sendRedirect("../error.jsp?id=1");
        }

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}
