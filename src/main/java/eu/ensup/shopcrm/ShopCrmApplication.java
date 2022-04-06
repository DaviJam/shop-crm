package eu.ensup.shopcrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ensup.shopcrm.domain.CustomResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.OutputStream;

@SpringBootApplication
@Controller
public class ShopCrmApplication {

	@GetMapping({"/home","/"})
	public String home(){
		return "welcome";
	}

	@GetMapping("/register")
	public String register(){
		return "register-page";
	}

	@GetMapping("/login")
	public String login(Model model){
		return "login-page";
	}

	@GetMapping("/error")
	public String error(){
		return "error";
	}

	@GetMapping("/access-denied")
	public String accessDenied(){
		CustomResponse customResponse = new CustomResponse( 403, "Forbidden");
		return "access-denied";
	}

	@GetMapping("/unauthorized")
	public String unauthorized(){
		CustomResponse customResponse = new CustomResponse( 401, "Unauthorized");
		return "unauthorized";
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopCrmApplication.class, args);
	}
}
