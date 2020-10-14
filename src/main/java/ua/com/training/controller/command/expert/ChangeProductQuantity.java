package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeProductQuantity implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = new StockDao().findAllProducts(0, new StockDao().getRowsCount());
        request.setAttribute("allProducts", products);
        return "commodityExpertChangeProductQuantity.jsp";
    }
}
