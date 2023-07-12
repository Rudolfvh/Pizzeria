package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.database.entity.Role;
import spring.dto.CreateOrderDto;
import spring.dto.CustomerDto;
import spring.service.CustomerService;
import spring.service.OrderService;
import spring.service.PizzaService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
@SessionAttributes("user")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final PizzaService pizzaService;

    @GetMapping()
    public String orderPage(Model model, @ModelAttribute("user") Optional<CustomerDto> customerDto) {
        model.addAttribute("pizzas", pizzaService.findAll());

            if (customerDto.get().getRole().equals(Role.ADMIN)) {
                return "admins/adminOrder";
            } else {
                return "orders/makeOrder";
            }
    }

    @PostMapping()
    public String makeOrder(@ModelAttribute("pizza_name") String pizza,
                            @ModelAttribute("user") CustomerDto customerDto, Model model) {
        var orderDto = CreateOrderDto.builder().customer(customerService.find(customerDto.getPhone()).get())
                .pizza(pizzaService.find(pizza).get())
                .dateGet(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())
                .build();
        orderService.create(orderDto);

        if (customerDto.getRole().equals(Role.ADMIN)) {
            return "redirect:admin/orderlist";
        } else {
            return "redirect:user/orderlist";
        }
    }
}
