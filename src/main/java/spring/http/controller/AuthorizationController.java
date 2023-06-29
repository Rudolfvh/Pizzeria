package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.dto.LoginDto;
import spring.dto.RegDto;
import spring.service.CustomerService;

@Controller
@RequiredArgsConstructor
@SessionAttributes("sessionCustomer")
public class AuthorizationController {

    private final CustomerService customerService;

    @GetMapping("/login")
    public String loginPage(Model model,
                            @ModelAttribute("userLog") LoginDto loginDto) {
        model.addAttribute("loginDto", loginDto);
        return "authentication/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") @Validated LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:" + "/login";
        }
        var sessionCustomer = customerService.login(loginDto);
        model.addAttribute("sessionCustomer", sessionCustomer);
        return "redirect:" + "/main";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:" + "/login";
    }
}
