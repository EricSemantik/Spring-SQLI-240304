package spring.formation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/accueil")
	public String welcome() {
		return "home";
	}
	
}
