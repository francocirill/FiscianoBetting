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

@WebServlet("/AdminSquadra")
public class AdminSquadraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkAdmin(request);
        SquadraDAO squadraDAO= new SquadraDAO();

        int punteggio = Integer.parseInt(request.getParameter("punteggio"));
        int golfatti =Integer.parseInt(request.getParameter("golfatti"));
        int golsubiti = Integer.parseInt(request.getParameter("golsubiti"));
        String id=request.getParameter("id");
        
            Squadra squadra = new Squadra();
            squadra.setId(id);
            squadra.setPunteggio(punteggio);
            squadra.setGolsubiti(golsubiti);
            squadra.setGolfatti(golfatti);
            squadraDAO.doUpdate(squadra);
            request.setAttribute("squadra",squadra);
            request.setAttribute("notifica", "squadra modificata con successo.");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminSquadra.jsp");
        requestDispatcher.forward(request, response);
    }
}
