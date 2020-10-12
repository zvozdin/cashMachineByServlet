package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.ChecksDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteOrder implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String code = request.getParameter("check_code");

        if (new ChecksDao().deleteOrderByCheckCode(Integer.parseInt(code))) {
            session.setAttribute("error", String.format("order #%s is deleted successfully", code));
        } else {
            session.setAttribute("error", "there is no checks today");
        }

        return "error.jsp";
    }
}
