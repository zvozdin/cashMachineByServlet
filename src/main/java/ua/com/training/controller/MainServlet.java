package ua.com.training.controller;

import ua.com.training.dao.Roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

public class MainServlet extends HttpServlet {

    // todo make logout by including jsp
    // todo error page by mapping error
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/")) {
            request.setAttribute("roles", EnumSet.allOf(Roles.class));
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        if (action.startsWith("/login")) {
            HttpSession session = request.getSession();
            String role = session.getAttribute("role").toString();
            switch (role) {
                case "SENIOR_CASHIER":
                    // todo database. retrieve and check for null or by Optional
                    if (true) {
                        String password = request.getParameter("password");
                        response.sendRedirect("seniorCashier.jsp");
                    }
                    break;
                default:
                        // do nothing
            }
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length());
    }
}