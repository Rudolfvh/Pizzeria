package spring.http.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.database.entity.Role;
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
            var id = user.get().getId();
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/workers";
            } else {
                return "redirect:/worker";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
