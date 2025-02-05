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
@WebServlet("/Registrazione")
public class RegistratiServlet extends HttpServlet {
    private UtenteDAO utenteDAO = new UtenteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("utente") != null) {
            throw new MyServletException("Utente loggato.");
        }

        String username = request.getParameter("username");
        if (!(username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+$"))) {    //soltanto una parola(prima ed ultima)
            throw new MyServletException("Username non valido.");
        }

        String password = request.getParameter("password");
        if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
            throw new MyServletException("Password non valida.");
        }

        String passwordConferma = request.getParameter("passwordConferma");
        if (!password.equals(passwordConferma)) {
            throw new MyServletException("Password e conferma differenti.");
        }

        String nome = request.getParameter("nome");
        if (!(nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {  //caratteri speciali tabella ascii
            throw new MyServletException("Nome non valido.");
        }

        String email = request.getParameter("email");
        if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {    //parola+quante altre parole vuoi+@+parola+ altre parole+parola
            throw new MyServletException("Email non valida.");
        }

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setNome(nome);
        utente.setEmail(email);
        utenteDAO.doSave(utente);
        request.getSession().setAttribute("utente", utente);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/registrazioneSuccesso.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

