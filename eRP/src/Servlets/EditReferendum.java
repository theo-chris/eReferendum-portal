package Servlets;

import Bean.DatabaseChecker;
import Domain.McQuestion;
import Domain.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditReferendum extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res)  {

        HttpSession session = req.getSession();

        DatabaseChecker dbc = new DatabaseChecker();
        McQuestion mcQuestion = dbc.getMcQuestion();
        session.setAttribute("refQuest",mcQuestion);
        try {
            res.sendRedirect("../editReferendum.jsp");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
        doGet(req,res);
    }
}
