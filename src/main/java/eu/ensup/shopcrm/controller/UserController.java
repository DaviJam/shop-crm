package eu.ensup.shopcrm.controller;

import eu.ensup.shopcrm.domain.User;
import eu.ensup.shopcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;


@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

//    @GetMapping("/{id}")
//    public String get(@PathVariable("id") String name, Model model){
//        model.addAttribute("name",this.userService.getUser(name).getName());
//        return "greeting";
//    }

    @GetMapping("/home")
    public String home(){
        return "welcome";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login-page";
    }

    @GetMapping("/register")
    public String register(){
        return "register-page";
    }

    @PostMapping("/signin")
    public String signin(User user, Model model){
        String ret = "welcome";
        User fetchedUser = this.userService.getUser(user.getName());
        if(fetchedUser != null && passwordEncoder.matches(fetchedUser.getPassword().subSequence(0,fetchedUser.getPassword().length()),user.getPassword())){
            model.addAttribute("message", "Mot de passe erronée");
            ret = "redirect:/user/login";
            return ret;
        } else if (fetchedUser == null){
            model.addAttribute("message", "Vous n'avez pas de compte chez nous. Veuillez en créer un.");
            model.addAttribute("error", "true");
            ret = "login-page";
        } else {
            model.addAttribute("name", fetchedUser.getName());
        }
        return ret;
    }

    @PostMapping(path="/signup")
    public String signup(User user, Model model){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.register(user);
        return "redirect:/user/login";
    }
}
