package controller;

import model.Partita;
import model.PartitaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(name="MyInit", urlPatterns="/MyInit", loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        PartitaDAO partitaDAO = new PartitaDAO();
        List<Partita> partite = partitaDAO.doRetrieveAll();
        getServletContext().setAttribute("partite", partite);
        super.init();
    }
// non è necessario né doGet né doPost. La usiamo solo per inserire le categorie// nel livello servlet context al caricamento dell’applicazione
// (perché servlet context e non Sessione?)
}