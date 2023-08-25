package mx.edu.utez.evaluacion_jass.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.evaluacion_jass.models.DaoUser;
import mx.edu.utez.evaluacion_jass.models.Incident.DaoIncidents;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ServletCharge", urlPatterns = {
        "/charge/main",
        "/charge/register",
        "/charge/update",
        "/charge/delete",
        "/charge/logout",
        "/charge/inactiva",
        "/charge/active"
})

public class ServletCharge extends HttpServlet {
    private Long id;
    private String action;
    private String redirect = "/charge/main";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action) {
            case "/charge/main":
                req.setAttribute("incidents", new DaoUser().findAll());
                redirect = "/views/charge.jsp";
                break;
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/charge/active":
                try {
                    id = Long.parseLong(req.getParameter("id"));
                    if (new DaoIncidents().charge_active(id)) {
                        redirect = "/charge/main?result=" + true
                                + "&message=" + URLEncoder.encode
                                ("Usuario activado correctamente", StandardCharsets.UTF_8);
                    } else {
                        redirect = "/charge/main?result=" + false
                                + "&message=" + URLEncoder.encode
                                ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    redirect = "/charge/main?result=" + false
                            + "&message=" + URLEncoder.encode
                            ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                }
                break;
            case "/charge/inactiva":
                try {
                    id = Long.parseLong(req.getParameter("id"));
                    if (new DaoIncidents().charge_inactive(id)) {
                        redirect = "/charge/main?result=" + true
                                + "&message=" + URLEncoder.encode
                                ("Usuario rechazado correctamente", StandardCharsets.UTF_8);
                    } else {
                        redirect = "/charge/main?result=" + false
                                + "&message=" + URLEncoder.encode
                                ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    redirect = "/charge/main?result=" + false
                            + "&message=" + URLEncoder.encode
                            ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                }
                break;

        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
