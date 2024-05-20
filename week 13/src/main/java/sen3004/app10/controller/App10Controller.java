package sen3004.app10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class App10Controller {

	@GetMapping("/")
	public String defaultPage() {
		return "member-page";
	}

	@GetMapping("/member-page.html")
	public String displayPage() {
		return "member-page";
	}

	@GetMapping("/login.html")
	public String displayLoginPage(Model model, HttpServletRequest request) {
		model.addAttribute("req", request);
		return "login";
	}

}
