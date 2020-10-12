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

        List<Check> checks = new ChecksDao().findAllChecks();
        if (checks.isEmpty()) {
            session.setAttribute("error", "there is no checks today");
            return "error.jsp";
        }

        session.setAttribute("checks", checks);
        return "seniorDeleteCheck.jsp";
    }
}
