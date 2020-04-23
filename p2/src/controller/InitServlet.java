package controller;

import model.Partita;
import model.PartitaDAO;
import model.Squadra;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="MyInit", urlPatterns="/MyInit", loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        PartitaDAO partitaDAO = new PartitaDAO();
        ServletContext context=getServletContext();

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
        

        super.init();
    }
// non è necessario né doGet né doPost. La usiamo solo per inserire le categorie// nel livello servlet context al caricamento dell’applicazione
// (perché servlet context e non Sessione?)
}