package controller;

import model.Squadra;
import model.SquadraDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminSquadraForm")
public class AdminSquadraFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SquadraDAO squadraDAO= new SquadraDAO();
        Squadra s=squadraDAO.doRetrieveById(request.getParameter("id"));
        request.setAttribute("squadra",s);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminSquadra.jsp");
        requestDispatcher.forward(request, response);

    }
}
