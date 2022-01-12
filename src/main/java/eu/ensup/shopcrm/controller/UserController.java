package eu.ensup.shopcrm.controller;

import eu.ensup.shopcrm.domain.User;
import eu.ensup.shopcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;


@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/{id}")
//    public String get(@PathVariable("id") String name, Model model){
//        model.addAttribute("name",this.userService.getUser(name).getName());
//        return "greeting";
//    }

    @GetMapping("/")
    public String home(){
        return "greeting";
    }

    @PostMapping("/signup")
    public String register(@RequestBody User user){
        this.userService.register(user);
        return "register";

    }

}
