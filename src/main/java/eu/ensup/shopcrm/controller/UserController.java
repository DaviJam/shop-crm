package eu.ensup.shopcrm.controller;

import eu.ensup.shopcrm.domain.Users;
import eu.ensup.shopcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/work")
    @Secured("USER")
    public String work(){
        return "work-page";
    }

    @PostMapping(path="/signup")
    public String signup(Users user, Model model){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.register(user);
        return "redirect:/user/login";
    }
}
