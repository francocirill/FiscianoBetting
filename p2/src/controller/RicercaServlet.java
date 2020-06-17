package controller;

import model.Partita;
import model.PartitaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
    PartitaDAO partitaDAO=new PartitaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testo=request.getParameter("testo");
        if(testo!=null)
        {
            List<Partita> partite = partitaDAO.doRetrieveBySquadre(testo + "*");
            request.setAttribute("partite",partite);
            request.setAttribute("ricerca",testo);
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/ricerca.jsp");
        requestDispatcher.forward(request, response);
    }
}
