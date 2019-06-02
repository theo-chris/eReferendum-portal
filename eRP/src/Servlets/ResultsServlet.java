package Servlets;

import Bean.DatabaseChecker;
import Domain.McOptions;
import Domain.Vote;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        DatabaseChecker dbc = new DatabaseChecker();
        List<Vote> totalVotes = dbc.collectVotes();
        List<McOptions> choices = dbc.getOptions(1);

        int sumOfVotes = totalVotes.size();

        Map<Integer,Double> countVotes = new HashMap<Integer,Double>();
        Map<Integer,Double> percentages = new HashMap<Integer,Double>();

        for(int i=0;i<choices.size();i++)
            countVotes.put(choices.get(i).getOptionID(),0.0);
        if(sumOfVotes>0){
            for(int i =0;i<sumOfVotes;i++){
                Vote vote = totalVotes.get(i);
                int choiceID = vote.getAnswerID();
                if(countVotes.containsKey(choiceID)){
                    countVotes.put(choiceID,countVotes.get(choiceID)+1);
                }else{
                    countVotes.put(choiceID, 1.0);

                }
            }
        }
        for(int i=1;i<=choices.size();i++){

            Double percent = countVotes.get(i)/sumOfVotes *100;
            percentages.put(i,percent);
        }
        HttpSession se = req.getSession();
        se.setAttribute("votes", countVotes);
        se.setAttribute("sumOfVotes", sumOfVotes);
        se.setAttribute("percentages", percentages);
        se.setAttribute("mcQuestion", dbc.getMcQuestion());
        se.setAttribute("answers", choices);


        try {
            res.sendRedirect("/results.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
        doGet(req, res);
    }

}
