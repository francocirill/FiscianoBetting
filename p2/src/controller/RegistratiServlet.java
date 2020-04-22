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

@WebServlet("/RegistratiServlet")
public class RegistratiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = new Utente();
        utente.setUsername(request.getParameter("user"));
        utente.setPassword(request.getParameter("pass"));
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setEmail(request.getParameter("email"));
        UtenteDAO service=new UtenteDAO();
        String address;
        //salva
        try {

            service.doSave(utente);
            request.getSession().removeAttribute("utente");
            request.getSession().setAttribute("utente", utente);
            response.sendRedirect(".");
        }catch (RuntimeException e)
        {
            address="/WEB-INF/results/errore.jsp";
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}
