package jpa.servlet;

import jpa.EntityManagerHelper;
import jpa.Tableau;
import jpa.dao.TableauDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name="information_tableau", urlPatterns = "/afficherTableau")
public class InformationTableau extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        rep.setContentType("text/html");

        PrintWriter out = rep.getWriter();

        TableauDAO daoTab = new TableauDAO(EntityManagerHelper.getEntityManager());
        List<Tableau> listeTab = daoTab.getAllTableau();

        String dyn = "<h2>Affichage des tableaux </h2>";
        dyn += "<table style = 'border-collapse:collapse; border:1px solid black;'>" +
                "<tr style = 'border-collapse:collapse; border:1px solid black;'/>" +
                "<th>Id du tableau" +
                "</th>" +
                "<th>Nom du projet" +
                "</th>"+
                "<th>Reference de section"+
                "</th>" +
                "</tr>" ;
        for(Tableau tab : listeTab) {
            dyn +=
                    "<tr style = 'border-collapse:collapse; border:1px solid black;'>>" +
                            "<td>" + tab.getIdTableau() +
                            "</td>" +
                            "<td>" + tab.getName() +
                            "</td>" +
                            "<td>" + tab.getSections() +
                            "</td>" +
                            "</tr>";

        }
        dyn += "</table>";
        String html = "<html> " +
                "<body>" +
                dyn +
                "</body>" +
                "</html>";
        out.println(html);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        rep.setContentType("text/html");

        PrintWriter out = rep.getWriter();

        TableauDAO tableauDAO = new TableauDAO(EntityManagerHelper.getEntityManager());
        Tableau tableau = new Tableau();
        tableau.setName(req.getParameter("nom"));
        tableauDAO.enregistrer(tableau);

        out.println("Enregistrement effectué avec succès");
    }
}
