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

@WebServlet("/AdminPartitaServlet")
public class AdminPartitaServlet extends HttpServlet {
    private final PartitaDAO partitaDAO = new PartitaDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServletException.checkAdmin(request);

        String idstr = request.getParameter("id");
        if (idstr != null) {
            if (request.getParameter("rimuovi") != null) {
                partitaDAO.doDelete(Integer.parseInt(idstr));
                request.setAttribute("notifica", "partita rimosso con successo.");
            } else {
                Partita partita;
                Double quota1= Double.parseDouble(request.getParameter("quota1"));
                Double quota2= Double.parseDouble(request.getParameter("quota2"));
                Double quota3= Double.parseDouble(request.getParameter("quota3"));
                
                if (!quota1.isNaN() && !quota2.isNaN() && !quota3.isNaN()) {
                    // modifica partita
                        partitaDAO.doUpdate(quota1,quota2,quota3,Integer.parseInt(idstr));
                        request.setAttribute("notifica", "partita modificato con successo.");
                    }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminpartita.jsp");
        requestDispatcher.forward(request, response);
    }

}

