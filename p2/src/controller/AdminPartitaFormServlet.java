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
import java.util.List;

@WebServlet("/AdminPartitaForm")
public class AdminPartitaFormServlet extends HttpServlet {
    private final PartitaDAO partitaDAO=new PartitaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operazione=request.getParameter("operazione");   //operazione
        request.setAttribute("operazione", operazione);
        int id= Integer.parseInt(request.getParameter("id"));
        PartitaDAO partitaDAO=new PartitaDAO();
        Partita p= partitaDAO.doRetrieveById(id);    //partita da modificare o eliminare
        request.setAttribute("partita",p);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminPartita.jsp");
        requestDispatcher.forward(request, response);
    }
}
