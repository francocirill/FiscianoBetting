package controller;

import model.PartitaDAO;
import model.Schedina;
import model.Scommessa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/SchedinaAjax")
public class SchedinaAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String esito=request.getParameter("esito");
        PartitaDAO partitaDAO = new PartitaDAO();
        HttpSession session=request.getSession();
        ArrayList<Scommessa> schedina;
        if(session.getAttribute("schedina")==null) { //schedina non ancora presente la creo
            schedina = new ArrayList<Scommessa>();
            Scommessa s = new Scommessa();
            s.setEsito(esito);
            s.setP(partitaDAO.doRetrieveById(id));
            schedina.add(s);
            session.setAttribute("schedina", schedina);

        }
        else {
            schedina = (ArrayList<Scommessa>) session.getAttribute("schedina");

            Scommessa s = new Scommessa();
            s.setEsito(esito);
            s.setP(partitaDAO.doRetrieveById(id));
            System.out.println("esito:"+esito+" id:"+id);

            String es="";
            for (Scommessa s1 : schedina) {   //controllo se ci sta una partita con lo stesso id e la rimuovo
                if (s1.getP().getId() == id) {
                    es=s1.getEsito();
                    schedina.remove(s1);
                    System.out.println("partita rimossa");
                    break;
                }
            }
            if(!es.equals(esito))  //non clicco sulla stessa quota
            {
                schedina.add(s);
                System.out.println("partita aggiunta");
            }
            session.removeAttribute("schedina");
            System.out.println("schedina rimossa");
            session.setAttribute("schedina", schedina);
            System.out.println("schedina aggiunta");
        }




    }
}
