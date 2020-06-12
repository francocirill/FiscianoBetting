package controller;

import model.Partita;
import model.PartitaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminPartitaForm")
public class AdminPartitaFormServlet extends HttpServlet {
    private final PartitaDAO partitaDAO=new PartitaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        List<Partita> Seriea=partitaDAO.doRetrieveByCampionato(1);
        List<Partita> LaLiga=partitaDAO.doRetrieveByCampionato(2);
        List<Partita> Ligue1=partitaDAO.doRetrieveByCampionato(3);
        List<Partita> Bundesliga=partitaDAO.doRetrieveByCampionato(4);
        List<Partita> Premier=partitaDAO.doRetrieveByCampionato(5);
        List<Partita> Eredivise=partitaDAO.doRetrieveByCampionato(6);
        List<Partita> Serieb=partitaDAO.doRetrieveByCampionato(7);

        request.setAttribute("SerieA",Seriea);
        request.setAttribute("SerieB",Serieb);
        request.setAttribute("Premier League",Premier);
        request.setAttribute("LaLiga",LaLiga);
        request.setAttribute("Bundesliga",Bundesliga);
        request.setAttribute("Ligue1",Ligue1);
        request.setAttribute("Eredivise",Eredivise);
        */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminPartita.jsp");
        requestDispatcher.forward(request, response);
    }
}
