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

@WebServlet("/AdminPartita")
public class AdminPartitaServlet extends HttpServlet {
    private final PartitaDAO partitaDAO = new PartitaDAO();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkAdmin(request);
/*
        String idstr = request.getParameter("id");
        if (idstr != null) {
            if (request.getParameter("rimuovi") != null) {
                partitaDAO.doDelete(Integer.parseInt(idstr));
                request.setAttribute("notifica", "partita rimosso con successo.");
            } else {
*/
                Double quota1 = Double.parseDouble(request.getParameter("quota1"));
                Double quota2 = Double.parseDouble(request.getParameter("quota2"));
                Double quota3 = Double.parseDouble(request.getParameter("quota3"));
                if (!quota1.isNaN() && !quota2.isNaN() && !quota3.isNaN()) {
                   // if (idstr.isEmpty()) { // aggiunta nuovo partita
                        Partita partita = new Partita();
                        partita.setQuota1(quota1);
                        partita.setQuota2(quota2);
                        partita.setQuota3(quota3);
                        partita.setIdsquadra1(request.getParameter("squadra1"));
                        partita.setIdsquadra2(request.getParameter("squadra2"));
                        partita.setData(request.getParameter("data"));
                        partita.setOra(request.getParameter("ora"));
                        partitaDAO.doSave(partita);
                        request.setAttribute("notifica", "Prodotto aggiunto con successo.");
                        /*
                    } else {
                        // modifica partita
                        partitaDAO.doUpdate(quota1, quota2, quota3, Integer.parseInt(idstr));
                        request.setAttribute("notifica", "partita modificato con successo.");
                    }
                }
            }*/
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminPartita.jsp");
            requestDispatcher.forward(request, response);
        }

}

