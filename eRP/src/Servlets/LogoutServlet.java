package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession se=req.getSession();
        se.removeAttribute("user");
        res.sendRedirect("../login.jsp");
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        doGet(req,res);
    }
}
