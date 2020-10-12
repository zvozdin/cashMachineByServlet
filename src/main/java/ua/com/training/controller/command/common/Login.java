package ua.com.training.controller.command.common;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.UserDao;
import ua.com.training.dao.entity.Roles;
import ua.com.training.dao.entity.User;
import ua.com.training.utility.SessionProductsAttribute;

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
        roleActivities.put(Roles.SENIOR_CASHIER, new LinkedHashSet<>(Arrays.asList("cancel order", "cancel product", "make X report")));
        roleActivities.put(Roles.CASHIER, new LinkedHashSet<>(Arrays.asList("open", "change", "close")));
        roleActivities.put(Roles.COMMODITY_EXPERT, new LinkedHashSet<>(Arrays.asList("view", "add", "change")));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        HttpSession session = request.getSession();

        if (isInputDataNotValid(login, password, role)) {
            session.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
            return "error.jsp";
        }

        User user = new UserDao().findUserByLoginAndPassword(login, password);
        if (isInputLoginAndRoleValidWithDBUser(login, role, user)) {
            setRoleAndUserAttributes(request, role, user);
            return "mainUser.jsp";
        }

        session.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
        return "error.jsp";
    }

    private boolean isInputDataNotValid(String login, String password, String role) {
        if (login == null || login.isEmpty()
                || password == null || password.isEmpty()
                || role == null || role.isEmpty()
        ) {
            return true;
        }

        return false;
    }

    private void setRoleAndUserAttributes(HttpServletRequest request, String role, User user) {
        HttpSession session = request.getSession();
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

        SessionProductsAttribute.setSessionAttributeProductsActualData(request);
    }

    private boolean isInputLoginAndRoleValidWithDBUser(String login, String role, User user) {
        return user != null && user.getLogin().equals(login) && user.getRole().name().equals(role);
    }
}
