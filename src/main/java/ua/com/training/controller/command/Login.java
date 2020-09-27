package ua.com.training.controller.command;

import ua.com.training.dao.UserDao;
import ua.com.training.dao.entity.Roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login implements Action {

    private static final String REGISTRATION_FIELDS_NOT_CORRECT = "Login or Password aren't correct";
    private static Map<Roles, List<String>> roleActivities;

    public Login() {
        roleActivities = new HashMap<>();
        roleActivities.put(Roles.SENIOR_CASHIER, Arrays.asList("cancel_order", "cancel_order_product", "make_Z_report"));
        roleActivities.put(Roles.CASHIER, Arrays.asList("create", "add", "change", "close"));
        roleActivities.put(Roles.COMMODITY_EXPERT, Arrays.asList("view", "add", "change"));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();

        if (new UserDao().findUserByRoleAndLoginAndPassword(role, login, password) != null) {
            switch (role) {
                case "SENIOR_CASHIER":
                    setLoginSessionAttributes(login, session, Roles.SENIOR_CASHIER);
                    return "seniorCashier.jsp";
                case "CASHIER":
                    setLoginSessionAttributes(login, session, Roles.CASHIER);
                    return "cashier.jsp";
                case "COMMODITY_EXPERT":
                    setLoginSessionAttributes(login, session, Roles.COMMODITY_EXPERT);
                    return "commodityExpert.jsp";
            }
        }
        request.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
        return "error.jsp";
    }

    private void setLoginSessionAttributes(String login, HttpSession session, Roles role) {
        session.setAttribute("LOGIN_USER", login);
        session.setAttribute("ROLE", role);
        session.setAttribute(role + "activities", roleActivities.get(role));
    }
}