package Servlets;

import Bean.DatabaseChecker;
import Domain.McOptions;
import Domain.McQuestion;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplyEdit extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {


        System.out.println("MPIKA SAS GAMISA");

        System.out.println(req.getParameter("num"));
        int optionNum = Integer.parseInt(req.getParameter("num"));


        List<McOptions> choices = new ArrayList<>();


        String optionStr;

        for (int counter = 0 ; counter < optionNum; counter++){

            System.out.println(req.getParameter(Integer.toString(counter)));
            McOptions actualOption = new McOptions();
            actualOption.setOptionID(counter+1);
            optionStr = req.getParameter(Integer.toString(counter));
            actualOption.setOption(optionStr);

            choices.add(actualOption);

        }

            DatabaseChecker dbc = new DatabaseChecker();

            String questionStr = req.getParameter("questStr");
        System.out.println(questionStr);
            McQuestion refQuestion = new McQuestion(1,questionStr,1);
            refQuestion.setChoices(choices);
            dbc.setRefQuestion(refQuestion);


            try{
                res.sendRedirect("../electionCom.jsp");

            }catch(IOException e){
                e.printStackTrace();
            }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        doPost(req, res);
    }
}
