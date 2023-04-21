package servlet;


import dto.CreateOrderDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CustomerService;
import service.OrderService;
import service.PizzaService;
import utils.JspHelper;



import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final CustomerService customerService = CustomerService.getInstance();
    private final PizzaService pizzaService = PizzaService.getInstance();
    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("order")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getSession().getAttribute("phone").toString();
        String password = req.getSession().getAttribute("password").toString();

        var orderDto = CreateOrderDto.builder()
                .pizzaNameId(pizzaService.find(req.getParameter("pizza_name")))
                .customerId(customerService.find(phone,password))
                .dateGet(Date.valueOf(LocalDate.now()))
                .build();
        try {
            orderService.create(orderDto);
            resp.sendRedirect("/order");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
