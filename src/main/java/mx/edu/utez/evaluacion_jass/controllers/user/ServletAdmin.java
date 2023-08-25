package mx.edu.utez.evaluacion_jass.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.evaluacion_jass.models.Incident.DaoIncidents;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ServletAdmin", urlPatterns = {
        "/admin/main",
        "/admin/delete",
        "/admin/logout",
        "/admin/activa",
        "/admin/inactiva"
})

public class ServletAdmin extends HttpServlet {
    private Long id;
    private String action;
    private String redirect = "/admin/main";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action) {
            case "/admin/main":
                redirect = "/views/admin.jsp";
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/admin/activa":
                try {
                    id = Long.parseLong(req.getParameter("id"));
                    if (new DaoIncidents()._admin_active(id)) {
                        redirect = "/admin/main?result=" + true
                                + "&message=" + URLEncoder.encode
                                ("Usuario activado correctamente", StandardCharsets.UTF_8);
                    } else {

                        redirect = "/admin/main?result=" + false
                                + "&message=" + URLEncoder.encode
                                ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    redirect = "/admin/main?result=" + false
                            + "&message=" + URLEncoder.encode
                            ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                }
                break;
            case "/admin/inactiva":

                try {
                    id = Long.parseLong(req.getParameter("id"));
                    if (new DaoIncidents().admin_inactive(id)) {
                        redirect = "/admin/main?result=" + true
                                + "&message=" + URLEncoder.encode
                                ("Usuario inactivo correctamente", StandardCharsets.UTF_8);
                    } else {
                        redirect = "/admin/main?result=" + false
                                + "&message=" + URLEncoder.encode
                                ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    redirect = "/admin/main?result=" + false
                            + "&message=" + URLEncoder.encode
                            ("No se pudo activar el usuario", StandardCharsets.UTF_8);
                }
                break;

        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

}
