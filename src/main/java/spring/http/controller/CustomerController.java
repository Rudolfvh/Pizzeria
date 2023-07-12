package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import spring.dto.CustomerDto;
import spring.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@SessionAttributes("user")
public class CustomerController {

    private final OrderService orderService;

    @GetMapping()
    public String loginPage() {
        return "user/main";
    }

    @GetMapping("/orderlist")
    public String orderList(Model model, @ModelAttribute("user") CustomerDto customerDto){
        model.addAttribute("orders", orderService.findByCustomerId(customerDto.getId().longValue()));
        return "orders/orderlist";
    }
}
