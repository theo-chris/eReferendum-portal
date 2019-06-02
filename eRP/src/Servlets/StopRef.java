package Servlets;

import Bean.DatabaseChecker;
import Domain.McQuestion;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class StopRef extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) {


        DatabaseChecker dbc = new DatabaseChecker();

        HttpSession session = req.getSession();

        session.setAttribute("status",dbc.getMcQuestion().getStatus());

        McQuestion mcQuestion = null;



                dbc.stopRef();
                mcQuestion = dbc.getMcQuestion();

                session.setAttribute("questionREF", mcQuestion);


            try{
                res.sendRedirect("/electionCom.jsp");
            }catch(IOException e){
                e.printStackTrace();
            }
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        doPost(req, res);
    }
}
