package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CampionatoServlet")
public class CampionatoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //nuova jsp che mostra solo partite del campionato passato dalla request
        //oppure stessa jsp con controllo su lega, se lega e' null allora mostra tutto
        String campionato=request.getParameter("campionato");
        String address="index.jsp";
        switch (campionato)
        {
            case "SerieA":
                request.setAttribute("lega",campionato);
                break;
            case "SerieB":
                request.setAttribute("lega",campionato);
                break;
            case "PremierLeague":
                request.setAttribute("lega",campionato);
                break;
            case "LaLiga":
                request.setAttribute("lega",campionato);
                break;
            case "Bundeliga":
                request.setAttribute("lega",campionato);
                break;
            case "Ligue1":
                request.setAttribute("lega",campionato);
                break;
            case "Eredivise":
                request.setAttribute("lega",campionato);
                break;
            case "tutte":
                request.removeAttribute("lega");
                break;
            default:
                address="errore.jsp";
                break;
        }
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
