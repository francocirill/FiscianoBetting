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

                if(request.getParameter("operazione").equals("aggiungi")){
                Double quota1 = Double.parseDouble(request.getParameter("quota1"));
                Double quota2 = Double.parseDouble(request.getParameter("quota2"));
                Double quota3 = Double.parseDouble(request.getParameter("quota3"));
                    if (!quota1.isNaN() && !quota2.isNaN() && !quota3.isNaN() && !request.getParameter("squadra1").equals(request.getParameter("squadra2"))) {
                        Partita partita = new Partita();
                        partita.setQuota1(quota1);
                        partita.setQuota2(quota2);
                        partita.setQuota3(quota3);
                        partita.setIdsquadra1(request.getParameter("squadra1"));
                        partita.setIdsquadra2(request.getParameter("squadra2"));
                        partita.setData(request.getParameter("data"));
                        partita.setOra(request.getParameter("ora"));
                        partitaDAO.doSave(partita);
                        request.setAttribute("operazione","aggiungi");
                        request.setAttribute("notifica", "Prodotto aggiunto con successo.");
                    }
            }
                else if(request.getParameter("operazione").equals("modifica"))
                {
                    Double quota1 = Double.parseDouble(request.getParameter("quota1"));
                    Double quota2 = Double.parseDouble(request.getParameter("quota2"));
                    Double quota3 = Double.parseDouble(request.getParameter("quota3"));
                    int id=Integer.parseInt(request.getParameter("id"));
                    if (!quota1.isNaN() && !quota2.isNaN() && !quota3.isNaN() ) {
                        Partita partita = new Partita();
                        partita.setId(id);
                        partita.setQuota1(quota1);
                        partita.setQuota2(quota2);
                        partita.setQuota3(quota3);
                        partita.setData(request.getParameter("data"));
                        partita.setOra(request.getParameter("ora"));
                        partitaDAO.doUpdate(partita);
                        request.setAttribute("operazione","modifica");
                        request.setAttribute("partita",partita);
                        request.setAttribute("notifica", "partita modificato con successo.");
                    }
                }
                else if(request.getParameter("operazione").equals("elimina"))
                {
                    request.setAttribute("operazione","elimina");
                    partitaDAO.doDelete(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("notifica", "partita rimosso con successo.");
                }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminPartita.jsp");
            requestDispatcher.forward(request, response);
        }

}

