package controller;

import model.PartitaDAO;
import model.Scommessa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/EliminaEvento")
public class EliminaEventoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id=Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession();
        ArrayList<Scommessa> schedina;
        if(session.getAttribute("schedina")==null) { //schedina non ancora presente la creo
            schedina = new ArrayList<Scommessa>();
        }
        else {
            schedina = (ArrayList<Scommessa>) session.getAttribute("schedina");
        }

        for (Scommessa s1 : schedina) {   //controllo se ci sta una partita con lo stesso id e la rimuovo
            if (s1.getP().getId() == id) {
                schedina.remove(s1);
                System.out.println("partita rimossa");
                break;
            }
        }
        session.removeAttribute("schedina");
        System.out.println("schedina rimossa");
        session.setAttribute("schedina", schedina);
        System.out.println("schedina aggiunta");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/schedina.jsp");
        requestDispatcher.forward(request, response);

    }
}
