package ua.com.training.controller.command.cashier;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.OrdersDao;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Close implements Action {

    private static final String CHECK_IS_EMPTY = "message02";
    private static final String CANT_CLOSE_ORDER = "message03";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Map<String, String[]> parameters = request.getParameterMap();

        if (isCartEmpty(parameters)) {
            session.setAttribute("report", CHECK_IS_EMPTY);
            return "report.jsp";
        }

        List<Product> products = getProductsFromClient(parameters);
        int checkCode = new OrdersDao()
                .save(products, (User) session.getAttribute("user"));
        if (checkCode > 0) {
            session.setAttribute("checkCode", checkCode);
            session.setAttribute("cart", products);
            return "check.jsp";
        }

        session.setAttribute("report", CANT_CLOSE_ORDER);
        return "report.jsp";
    }

    private boolean isCartEmpty(Map<String, String[]> map) {
        return map == null || map.size() == 0 ? true : false;
    }

    private List<Product> getProductsFromClient(Map<String, String[]> map) {
        List<Product> result = new ArrayList<>();
        List<Product> products = new StockDao().findAllProducts(0, new StockDao().getRowsCount());
        for (String productCode : map.keySet()) {
            Optional<Product> oProduct =
                    products.stream()
                            .filter(p -> p.getCode().equals(productCode))
                            .findFirst();

            if (oProduct.isPresent()) {
                Product product = oProduct.get();
                result.add(
                        new Product.ProductBuilder()
                                .id(product.getId())
                                .code(product.getCode())
                                .name(product.getName())
                                .name_UA(product.getName_UA())
                                .size(product.getSize())
                                .quantity(Integer.valueOf(map.get(productCode)[0]))
                                .price(product.getPrice())
                                .bill(Integer.valueOf(map.get(productCode)[0]) * product.getPrice())
                                .build());
            }
        }

        return result;
    }
}
