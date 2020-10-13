package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.ChecksDao;
import ua.com.training.dao.entity.Check;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Checks implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getRequestURI().substring(request.getContextPath().length());
        switch (action) {
            case "/cancelOrder":
                return getOrderRequest(session, new ChecksDao().findAllChecks());
            case "/cancelProduct":
                return getProductRequest(session, new ChecksDao().findAllChecksWithProducts());
            default:
                return "report.jsp";
        }
    }

    private String getProductRequest(HttpSession session, List<Check> checks) {
        if (isEmpty(session, checks))
            return "report.jsp";

        session.setAttribute("checks", checks);
        return "seniorChooseCheckForDeleteProduct.jsp";
    }

    private String getOrderRequest(HttpSession session, List<Check> checks) {
        if (isEmpty(session, checks)) {
            return "report.jsp";
        } else {
            session.setAttribute("checks", checks);
            return "seniorDeleteCheck.jsp";
        }
    }

    private boolean isEmpty(HttpSession session, List<Check> checks) {
        if (checks.isEmpty()) {
            session.setAttribute("report", "there is no checks today");
            return true;
        }
        return false;
    }
}
