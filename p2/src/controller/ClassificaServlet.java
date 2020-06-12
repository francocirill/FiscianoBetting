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
import java.util.ArrayList;

@WebServlet("/ClassificaServlet")
public class ClassificaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadraDAO squadradao= new SquadraDAO();
/*
        ArrayList<Squadra> seriea=squadradao.doRetrieveByCampionato("SerieA");
        ArrayList<Squadra> serieb=squadradao.doRetrieveByCampionato("SerieB");
        ArrayList<Squadra> premier=squadradao.doRetrieveByCampionato("PremierLeague");
        ArrayList<Squadra> liga=squadradao.doRetrieveByCampionato("LaLiga");
        ArrayList<Squadra> bundesliga=squadradao.doRetrieveByCampionato("Bundesliga");
        ArrayList<Squadra> ligue1=squadradao.doRetrieveByCampionato("Ligue1");
        ArrayList<Squadra> eredivise=squadradao.doRetrieveByCampionato("Eredivise");

        request.setAttribute("SerieA",seriea);
        request.setAttribute("SerieB",serieb);
        request.setAttribute("Premierleague",premier);
        request.setAttribute("LaLiga",liga);
        request.setAttribute("Bundesliga",bundesliga);
        request.setAttribute("Ligue1",ligue1);
        request.setAttribute("Eredivise",eredivise);
*/
        String address;
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("");
        dispatcher.forward(request, response);



    }
}
