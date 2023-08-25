package mx.edu.utez.evaluacion_jass.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.evaluacion_jass.models.User;
import mx.edu.utez.evaluacion_jass.models.Incident.DaoIncidents;
import mx.edu.utez.evaluacion_jass.models.Incident.Incident;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "Incidents",
        urlPatterns = {
                "/incident/incidents",
                "/incident/incident",
                "/incident/save",
                "/incident/accept",
                "/incident/decline",
                "/incident/view",
                "/incident/view-create",
                "/incident/enable",
        })
public class ServletIncident extends HttpServlet {
    private String action;
    private String redirect = "/incident/incidents";
    private String id;
    private String name;
    private String title;
    private String description;
    private Incident incident;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/incident/incidents":
                List<Incident> incidents = new DaoIncidents().findAll();
                req.setAttribute("incidents", incidents);
                redirect = "/views/user.jsp";
                break;
            case "/incident/view-create":
                redirect = "/views/create.jsp";
                break;
            default:
                System.out.println(action);
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();

        switch (action) {
            case "/incident/save":
                name = req.getParameter("name");
                title = req.getParameter("title");

                description = req.getParameter("description");
                incident = new Incident(0L, name, title, description, "PENDIENTE");
                boolean result = new DaoIncidents().save(incident);
                if (result)
                    redirect = "/incident/incidents?result= " + true + "&message=" + URLEncoder.encode
                            ("¡Éxito! Justificacion registrada correctamente.", StandardCharsets.UTF_8);
                else
                    redirect = "/incident/incidents?result= " + false + "&message=" + URLEncoder.encode
                            ("¡Error! Acción no realizada correctamente.", StandardCharsets.UTF_8);
                break;
            case "/incident/delete":
                id = req.getParameter("id");
                if (new DaoIncidents().delete(Long.parseLong(id)))
                    redirect = "/incident/incident?result= " + true
                            + "&message="
                            + URLEncoder.encode("¡Éxito! Se ha eliminado correctamente.",
                            StandardCharsets.UTF_8);
                else
                    redirect = "/incident/incident?result= "
                            + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente.",
                            StandardCharsets.UTF_8);
                break;

            default:
                redirect = "/incident/incident";
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
