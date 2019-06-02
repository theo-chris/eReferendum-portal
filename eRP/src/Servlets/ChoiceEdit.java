package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChoiceEdit extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {


        try{
                res.sendRedirect("../choiceEdit.jsp");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)  {

    }
}
