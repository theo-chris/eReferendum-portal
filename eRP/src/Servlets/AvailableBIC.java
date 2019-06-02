package Servlets;

import Bean.DatabaseChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AvailableBIC extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        String BIC=req.getParameter("BIC");

        try{
            if(!(BIC == null && BIC.length()==0)){
                DatabaseChecker dbc = new DatabaseChecker();
                if(dbc.isBICAvailable(BIC)){
                    out.println("{\"BIC_AVAILABLE\":true}");
                }else{
                    out.println("{\"BIC_AVAILABLE\":false}");
                }
            }

        }catch(Exception ex){
            out.println("{\"message\":\"exception\"}");
        }


        out.close();
    }
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
