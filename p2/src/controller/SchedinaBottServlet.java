package controller;

import model.Scommessa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SchedinaBott")
public class SchedinaBottServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        ArrayList<Scommessa> schedina;
        if(session.getAttribute("schedina")==null) { //schedina non ancora presente la creo
            schedina = new ArrayList<Scommessa>();}
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/schedina.jsp");
        requestDispatcher.forward(request, response);
    }
}
