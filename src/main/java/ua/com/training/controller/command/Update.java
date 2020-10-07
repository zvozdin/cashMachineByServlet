package ua.com.training.controller.command;

import ua.com.training.dao.StockDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Update implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        // todo implement check in jsp every field
        String code = request.getParameter("code");
        String value = request.getParameter("value");

        if (isInputDataNotValid(session, code, value)) {
            return "error.jsp";
        }

        if (new StockDao().updateQuantityProductByCode(code, Integer.parseInt(value))) {
            return "mainUser.jsp";
        } else {
            session.setAttribute("error", "can't update. Check data");
            return "error.jsp";
        }
    }

    private boolean isInputDataNotValid(HttpSession session, String code, String value) {
        if (code == null) {
            session.setAttribute("error", "choose product");
            return true;
        }

        if (value == null || Integer.parseInt(value) < 0) {
            session.setAttribute("error", "set product quantity and not less than 0");
            return true;
        }
        return false;
    }
}
