package controller;

import model.*;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SquadraAjax")
public class SquadraAjaxServlet extends HttpServlet {
    private final SquadraDAO squadraDAO=new SquadraDAO();
    private final CampionatoDAO campionatoDAO=new CampionatoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String campionato=request.getParameter("campionato");
        JSONArray prodJson = new JSONArray();
        if (!campionato.isEmpty()) {
            //Campionato c=campionatoDAO.doRetrieveById(campionato);

            List<Squadra> squadre=new ArrayList<>();
                    squadre=squadraDAO.doRetrieveByCampionato(Integer.parseInt(campionato));

            for (Squadra s : squadre) {
                prodJson.put(s.getId());
            }
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());

    }
}
