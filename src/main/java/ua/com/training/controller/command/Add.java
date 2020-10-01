package ua.com.training.controller.command;

import ua.com.training.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class Add implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Set<String> columns = new ProductDao().getColumns();
        columns.remove("id");
        request.getSession().setAttribute("columns", columns);
        return "commodityExpertAddProduct.jsp";
    }
}
