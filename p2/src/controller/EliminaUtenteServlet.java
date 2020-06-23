package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/EliminaUtente")
public class EliminaUtenteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyServletException.checkAdmin(request);
        UtenteDAO utenteDAO=new UtenteDAO();
        //elimina utente
        int id=Integer.parseInt(request.getParameter("id"));
        utenteDAO.doDelete(id);
        ArrayList<Utente> utenti=utenteDAO.doRetrieveAll();
        request.setAttribute("utenti",utenti);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminUtente.jsp");
        requestDispatcher.forward(request, response);
    }
}
