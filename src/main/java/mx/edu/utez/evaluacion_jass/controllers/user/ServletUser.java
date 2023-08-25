package mx.edu.utez.evaluacion_jass.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.evaluacion_jass.models.DaoUser;
import mx.edu.utez.evaluacion_jass.models.User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ServletUser",
        urlPatterns = {
                "/api/auth",
                "/api/auth/login",
                "/api/auth/logout",
                "/api/user/admin",
                "/api/user/user",
                "/api/user/charge",
                "/api/user/index",
                "/api/user/accept",
        })
public class ServletUser extends HttpServlet {
    String action, redirect = "/api/user/admin";
    HttpSession session;
    String username;
    String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/api/auth/logout":
                session = req.getSession();
                session.invalidate();
                redirect = "/index.jsp";
                break;
            case "/api/auth/login":
                redirect = "/index.jsp";
                break;
            case "/api/user/admin":
                req.setAttribute("incidents", new DaoUser().ListaIncidentes_Activos());
                redirect = "/views/admin.jsp";
                break;

            case "/api/user/charge":
                req.setAttribute("incidents", new DaoUser().findAll());
                redirect = "/views/charge.jsp";
                break;
            case "/api/user/user":
                req.setAttribute("incidents", new DaoUser().findAll());

                redirect = "/views/user.jsp";
                break;
        }
        req.getRequestDispatcher(redirect)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action) {
            case "/api/auth":
                username = req.getParameter("username");
                password = req.getParameter("password");
                try {
                    User user = new DaoUser().loadUserByUsernameAndPassword(username, password);
                    if (user != null) {
                        session = req.getSession();
                        session.setAttribute("user", user);
                        switch (user.getRole().getRole()) {
                            case "ADMIN_ROLE":
                                redirect = "/api/user/admin";
                                break;
                            case "CHARGE_ROLE":
                                redirect = "/api/user/charge";
                                break;
                            case "USER_ROLE":
                                redirect = "/api/user/user";
                                break;
                        }
                    } else {
                        throw new Exception("Credentials mismatch");
                    }
                } catch (Exception e) {
                    redirect = "/api/user/login?result=false&message=" + URLEncoder
                            .encode("Usuario y/o contraseña incorrecta",
                                    StandardCharsets.UTF_8);
                }
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
