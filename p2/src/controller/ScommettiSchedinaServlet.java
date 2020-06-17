package controller;

import model.SchedinaGiocata;
import model.SchedinaGiocataDAO;
import model.Scommessa;
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

@WebServlet("/ScommettiSchedina")
public class ScommettiSchedinaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se non è loggato rimanda alla registrazione
        if (request.getSession().getAttribute("utente") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/registrazioneForm.jsp");
            requestDispatcher.forward(request, response);
        } else {
            //se è loggato salva la schedina giocata
            int importo = Integer.parseInt(request.getParameter("importo"));
            HttpSession session = request.getSession();
            ArrayList<Scommessa> schedina = (ArrayList<Scommessa>) session.getAttribute("schedina");
            double totquote = 1;
            String testo = "";
            if (schedina != null && schedina.size() > 0 && importo >= 2) {

                for (Scommessa s1 : schedina) {
                    testo += s1.getP().getIdsquadra1() + "  " + s1.getP().getIdsquadra2() + "  ";
                    switch (s1.getEsito()) {
                        case "1":
                            totquote *= s1.getP().getQuota1();
                            testo += "1";
                            break;
                        case "2":
                            totquote *= s1.getP().getQuota2();
                            testo += "X";
                            break;
                        case "3":
                            totquote *= s1.getP().getQuota3();
                            testo += "2";
                            break;
                    }
                    testo += "\n";

                }
                Utente u = (Utente) session.getAttribute("utente");
                SchedinaGiocata sched = new SchedinaGiocata();

                sched.setImporto(importo);
                sched.setVincita(Math.floor(importo * totquote*100.0)/100.0);
                sched.setIdutente(u.getId());
                sched.setTesto(testo);
                SchedinaGiocataDAO schedinaGiocataDAO = new SchedinaGiocataDAO();
                schedinaGiocataDAO.doSave(sched); //salva schedina giocata
                session.removeAttribute("schedina"); //rimuove schedina dalla sessione
                request.setAttribute("notifica", "Schedina giocata");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/schedina.jsp");
                requestDispatcher.forward(request, response);

            } else {
                throw new MyServletException("Errore schedina");
            }


        }
    }
}