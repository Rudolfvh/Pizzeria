package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.dto.CreatePizzaDto;
import spring.dto.CustomerDto;
import spring.service.OrderService;
import spring.service.PizzaService;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    private final OrderService orderService;
    private final PizzaService pizzaService;

    @GetMapping()
    public String AdminPage() {
        return "admins/admin";
    }

    @GetMapping("/orderlist")
    public String orderList(Model model, @ModelAttribute("user") CustomerDto customerDto){
        model.addAttribute("orders", orderService.findByCustomerId(customerDto.getId().longValue()));
        return "admins/adminOrderList";
    }

    @GetMapping("/addNewPizza")
    public String addNewPizzaPage() {
        return "admins/addNewPizza";
    }

    @PostMapping("/addNewPizza")
    public String addNewPizza(@ModelAttribute("name") String name,
                              @ModelAttribute("cost") BigDecimal cost){

        var pizzaDto = CreatePizzaDto.builder()
                        .name(name)
                                .cost(cost)
                .build();
        pizzaService.create(pizzaDto);

        return "admins/admin";
    }

}
