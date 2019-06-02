package Servlets;

import Bean.DatabaseChecker;
import Domain.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RefVote extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)  {

        HttpSession session = req.getSession();
        User voter = (User)session.getAttribute("user");
        voter.setHasVoted(true);

        DatabaseChecker dbc = new DatabaseChecker();
        String userEmail = voter.getEmail();

        int refID = Integer.parseInt(req.getParameter("refID"));
        int refVote = Integer.parseInt(req.getParameter("optionID"));

        String refQuestion = req.getParameter("refQuest");

        dbc.voteSubmit(userEmail,refID,refVote,refQuestion);
        session.setAttribute("user",voter);

        try {
            res.sendRedirect("../mainVoter.jsp");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
        doGet(req,res);
    }
}
