package ua.com.training.controller;

import ua.com.training.dao.Roles;
import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

public class MainServlet extends HttpServlet {

    public static final String REGISTRATION_FIELDS_NOT_EMPTY = "Login and Password field can't be empty";
    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new ServiceImpl();
    }

    // todo make logout by including jsp

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.equals("/")) {
            request.setAttribute("roles", EnumSet.allOf(Roles.class));
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }

        if (action.equals("/login")) {
            HttpSession session = request.getSession();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String role = session.getAttribute("role").toString();
            if (!login.isEmpty() && !password.isEmpty()
                    && service.existUserByRoleAndLogin(role, login, password)) {
                session.setAttribute("role", role);
                switch (role) {
                    case "SENIOR_CASHIER":
                        response.sendRedirect("seniorCashier.jsp");
                        break;
                    case "CASHIER":
                        response.sendRedirect("general-error.jsp?error=" + REGISTRATION_FIELDS_NOT_EMPTY);
                        break;
                    case "COMMODITY_EXPERT":
                        response.sendRedirect("general-error.jsp?error=" + REGISTRATION_FIELDS_NOT_EMPTY);
                        break;
                }
            } else {
                response.sendRedirect("general-error.jsp?error=" + REGISTRATION_FIELDS_NOT_EMPTY);
            }
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length());
    }
}