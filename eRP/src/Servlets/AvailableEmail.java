package Servlets;

import Bean.DatabaseChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AvailableEmail extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        String email=req.getParameter("email");

        try{
            if(email!=null && email.length()!=0){
                DatabaseChecker dbO =new DatabaseChecker();
                if(dbO.existingEmail(email)){
                    out.println("{\"existing_user\":true}");
                }else{
                    out.println("{\"existing_user\":false}");
                }
            }

        }catch(Exception ex){
            out.println("{\"message\":\"exception\"}");
        }


        out.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
