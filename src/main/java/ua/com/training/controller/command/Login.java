package ua.com.training.controller.command;

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
        roleActivities.put(Roles.SENIOR_CASHIER, new LinkedHashSet<>(Arrays.asList("cancel_order", "cancel_order_product", "make_Z_report")));
        roleActivities.put(Roles.CASHIER, new LinkedHashSet<>(Arrays.asList("open", "changeCheck", "close")));
        roleActivities.put(Roles.COMMODITY_EXPERT, new LinkedHashSet<>(Arrays.asList("view", "add", "change")));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();

        if (login == null || login.isEmpty()
                || password == null || password.isEmpty()
                || role == null || role.isEmpty()
        ) {
            session.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
            return "error.jsp";
        }

        User user = new UserDao().findUserByLoginAndPassword(login, password);
        if (isInputLoginAndRoleValidWithDBUser(login, role, user)) {
            switch (role) {
                case "SENIOR_CASHIER":
                    session.setAttribute("activities", roleActivities.get(Roles.SENIOR_CASHIER));
                    break;
                case "CASHIER":
                    session.setAttribute("activities", roleActivities.get(Roles.CASHIER));
                    break;
                case "COMMODITY_EXPERT":
                    session.setAttribute("activities", roleActivities.get(Roles.COMMODITY_EXPERT));
                    break;
            }

            session.setAttribute("user", user);
            session.setAttribute("ROLE", Roles.valueOf(role));
            return "mainUser.jsp";
        }
        session.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
        return "error.jsp";
    }

    private boolean isInputLoginAndRoleValidWithDBUser(String login, String role, User user) {
        return user != null && user.getLogin().equals(login) && user.getRole().name().equals(role);
    }
}
