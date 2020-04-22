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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

             //accedi
            Utente utente = new Utente();
            UtenteDAO service=new UtenteDAO();
            String address;
            //cerca
            try {
                utente=service.doRetrieveByUsernamePassword(request.getParameter("username"),request.getParameter("password"));
                if(utente!=null) {
                    request.getSession().removeAttribute("utente");
                    request.getSession().setAttribute("utente", utente);
                }
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
