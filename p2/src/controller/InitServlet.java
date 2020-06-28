package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="MyInit", urlPatterns="", loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        CampionatoDAO campionatoDAO = new CampionatoDAO();
        ServletContext context=getServletContext();

        List<Campionato> c=campionatoDAO.doRetrieveAll();
        context.setAttribute("campionati",c);

        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartitaDAO partitaDAO=new PartitaDAO();
        List<Partita> p=partitaDAO.doRetrieveAll();

        request.setAttribute("partite",p);

        String address="WEB-INF/results/index.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}