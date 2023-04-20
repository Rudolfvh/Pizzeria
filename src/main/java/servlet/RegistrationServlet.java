package servlet;

import dto.CreateCustomerDto;
import entity.Role;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CustomerService;
import utils.JspHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final CustomerService customerService = CustomerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var customerDto = CreateCustomerDto.builder()
                .name(req.getParameter("name"))
                .phone(req.getParameter("phone"))
                .password(req.getParameter("pwd"))
                .role(req.getParameter("role"))
                .build();
        try {
            customerService.create(customerDto);
            resp.sendRedirect("/order");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
