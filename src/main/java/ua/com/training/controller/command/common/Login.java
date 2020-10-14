package ua.com.training.controller.command.common;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.UserDao;
import ua.com.training.dao.entity.Roles;
import ua.com.training.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class Login implements Action {

    private static final String REGISTRATION_FIELDS_NOT_CORRECT = "Login or Password aren't correct";
    private static Map<Roles, Set<String>> roleActivities;

    public Login() {
        roleActivities = new HashMap<>();
        roleActivities.put(Roles.SENIOR_CASHIER, new LinkedHashSet<>(Arrays.asList("cancelOrder", "cancelProduct", "makeXReport")));
        roleActivities.put(Roles.CASHIER, new LinkedHashSet<>(Arrays.asList("open", "changeCashier", "close")));
        roleActivities.put(Roles.COMMODITY_EXPERT, new LinkedHashSet<>(Arrays.asList("view", "add", "changeExpert")));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();

        User user = new UserDao().findUserByLoginAndPassword(login, password);
        if (isInputLoginAndRoleValidWithDBUser(login, role, user)) {
            session.setAttribute("activities", roleActivities.get(getRole(role)));
            session.setAttribute("user", user);
            session.setAttribute("ROLE", Roles.valueOf(role));
            return "mainUser.jsp";
        }

        session.setAttribute("report", REGISTRATION_FIELDS_NOT_CORRECT);
        return "report.jsp";
    }

    private Roles getRole(String role) {
        switch (role) {
            case "SENIOR_CASHIER":
                return Roles.SENIOR_CASHIER;
            case "CASHIER":
                return Roles.CASHIER;
            case "COMMODITY_EXPERT":
                return Roles.COMMODITY_EXPERT;
            default:
                return Roles.CASHIER;
        }
    }

    private boolean isInputLoginAndRoleValidWithDBUser(String login, String role, User user) {
        return user != null && login.equals(user.getLogin()) && role.equals(user.getRole().name());
    }
}
