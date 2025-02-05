package controller;

import model.Campionato;
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

@WebServlet("/CampionatoServlet")
public class CampionatoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //nuova jsp che mostra solo partite del campionato passato dalla request

        int campionato=Integer.parseInt(request.getParameter("id"));
        List<Campionato> campionati = (List<Campionato>) getServletContext().getAttribute("campionati");
        request.setAttribute("campionato", campionati.stream().filter(c -> c.getId() == campionato).findAny().get());

        String address="WEB-INF/results/campionato.jsp";
        PartitaDAO partitaDAO=new PartitaDAO();
        List<Partita> a=partitaDAO.doRetrieveByCampionato(campionato);
        request.setAttribute("partite",a);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
