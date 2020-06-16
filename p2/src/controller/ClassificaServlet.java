package controller;

import model.Squadra;
import model.SquadraDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ClassificaServlet")
public class ClassificaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadraDAO squadraDAO= new SquadraDAO();
        int id=Integer.parseInt(request.getParameter("id"));
        ArrayList<Squadra> classifica=squadraDAO.doRetrieveByCampionato(id);
        request.setAttribute("classifica",classifica);
        String nome=request.getParameter("nome");
        request.setAttribute("nome",nome);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/classifica.jsp");
        requestDispatcher.forward(request, response);



    }
}
