package spring.http.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.database.entity.Role;
import spring.dto.CreateCustomerDto;
import spring.dto.CustomerDto;
import spring.dto.LoginDto;
import spring.service.CustomerService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
//@SessionAttributes("user")
public class LoginController {

    private final CustomerService customerService;

    @GetMapping()
    public String loginPage() {
        return "user/login";
    }

    @PostMapping()
    public String login(@ModelAttribute("login") LoginDto loginDto,
                        RedirectAttributes redirectAttributes) {

        Optional<CustomerDto> user = customerService.login(loginDto);
        if(user.isPresent()) {
            redirectAttributes.addFlashAttribute("user", user.get());
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        }
        return "redirect:user/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model,
                               @ModelAttribute("userCreate") CreateCustomerDto customerDto) {

        model.addAttribute("userCreate",customerDto);
        model.addAttribute("roles",Role.values());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userCreate") @Validated CreateCustomerDto customerDto,
                               RedirectAttributes redirectAttributes) {

        if(customerService.find(customerDto.getPhone()).isPresent()) {
            return "redirect:/login/registration";
        }
        redirectAttributes.addFlashAttribute("user",customerService.create(customerDto));
        if (customerDto.getRole().equals(Role.ADMIN.name())){
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }

}
