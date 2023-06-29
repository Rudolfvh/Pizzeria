package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import spring.service.CustomerService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@SessionAttributes("user")
public class AdminController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public String findAll(Model model) {
        var customers = customerService.findAll();
        model.addAttribute("customer", customers);
        return "admin/adminStartPage";
    }

}
