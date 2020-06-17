package controller;

import model.SchedinaGiocata;
import model.SchedinaGiocataDAO;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/MostraSchedine")
public class MostraSchedineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("utente") == null)
        {
            throw new MyServletException("Errore utente non loggato");
        }
        else {
            Utente u = (Utente) session.getAttribute("utente");
            SchedinaGiocataDAO schedinaGiocataDAO = new SchedinaGiocataDAO();
            ArrayList<SchedinaGiocata> schedinegiocate= schedinaGiocataDAO.doRetrieveByIdutente(u.getId()); //cerca schedine di quell'utente
            request.setAttribute("schedinegiocate",schedinegiocate);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/schedineGiocate.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
