package controller;

import model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="MyInit", urlPatterns="/index", loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        CampionatoDAO campionatoDAO = new CampionatoDAO();
        ServletContext context=getServletContext();

        List<Campionato> c=campionatoDAO.doRetrieveAll();
        context.setAttribute("campionati",c);
        /*
        List<Partita> seriea=partitaDAO.doRetrieveByCampionato("SerieA");
        List<Partita> serieb=partitaDAO.doRetrieveByCampionato("SerieB");
        List<Partita> premier=partitaDAO.doRetrieveByCampionato("PremierLeague");
        List<Partita> liga=partitaDAO.doRetrieveByCampionato("LaLiga");
        List<Partita> bundesliga=partitaDAO.doRetrieveByCampionato("Bundesliga");
        List<Partita> ligue1=partitaDAO.doRetrieveByCampionato("Ligue1");
        List<Partita> eredivise=partitaDAO.doRetrieveByCampionato("Eredivise");

        context.setAttribute("SerieA",seriea);
        context.setAttribute("SerieB",serieb);
        context.setAttribute("Premierleague",premier);
        context.setAttribute("LaLiga",liga);
        context.setAttribute("Bundesliga",bundesliga);
        context.setAttribute("Ligue1",ligue1);
        context.setAttribute("Eredivise",eredivise);
        
        */
        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartitaDAO partitaDAO=new PartitaDAO();
        List<Partita> p=partitaDAO.doRetrieveAll();

        request.setAttribute("partite",p);

    }
}