package ua.com.training.controller.command;

import ua.com.training.dao.Roles;
import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Action {

    private static final String REGISTRATION_FIELDS_NOT_CORRECT = "Login or Password aren't correct";
    private Service service = new ServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();

        if (service.existUserByRoleAndLogin(role, login, password)) {
            switch (role) {
                case "SENIOR_CASHIER":
                    session.setAttribute("LOGIN_USER", login);
                    session.setAttribute("ROLE", Roles.SENIOR_CASHIER);
                    return "seniorCashier.jsp";
                case "CASHIER":
                    session.setAttribute("LOGIN_USER", login);
                    session.setAttribute("ROLE", Roles.CASHIER);
                    return "cashier.jsp";
                case "COMMODITY_EXPERT":
                    session.setAttribute("LOGIN_USER", login);
                    session.setAttribute("ROLE", Roles.COMMODITY_EXPERT);
                    return "commodityExpert.jsp";
            }
        }
        request.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
        return "error.jsp";
    }
}