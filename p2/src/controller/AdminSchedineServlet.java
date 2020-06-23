package controller;

import model.SchedinaGiocata;
import model.SchedinaGiocataDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/AdminSchedine")
public class AdminSchedineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MyServletException.checkAdmin(request);

        SchedinaGiocataDAO schedinaGiocataDAO = new SchedinaGiocataDAO();
        ArrayList<SchedinaGiocata> schedinegiocate= schedinaGiocataDAO.doRetrieveAll(); //cerca schedine di tutti fli utenti
        //cerca gli utenti delle schedine
        UtenteDAO utenteDAO=new UtenteDAO();
        for(int i=0;i<schedinegiocate.size();i++)
        {
            Utente u=new Utente();
            u=utenteDAO.doRetrieveById(schedinegiocate.get(i).getIdutente()); //ricerca utente
            schedinegiocate.get(i).setUsername(u.getUsername());  //aggiungi username
        }

        request.setAttribute("schedinegiocate",schedinegiocate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/schedineGiocate.jsp");
        requestDispatcher.forward(request, response);

    }
}
