package sen3004.app11.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class App11Controller {

	@GetMapping("/")
	public ModelAndView defaultPage(@AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("user-page");
		mv.addObject("loggedUser", user.getUsername());
		mv.addObject("roles", user.getAuthorities().toString().toLowerCase().replace("role_", ""));
		
		return mv;
	}
	
	@GetMapping("/user-page.html")
	public ModelAndView displayUserPage(@AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("user-page");
		mv.addObject("loggedUser", user.getUsername());
		mv.addObject("roles", user.getAuthorities().toString().toLowerCase().replace("role_", ""));
				
		return mv;
	}
	
	@GetMapping("/editor-page.html")
	public ModelAndView displayEditorPage(@AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("editor-page");
		mv.addObject("loggedUser", user.getUsername());
		mv.addObject("roles", user.getAuthorities().toString().toLowerCase().replace("role_", ""));
		
		return mv;
	}
	
	@GetMapping("/login.html")
	public String displayLoginPage(Model model, HttpServletRequest request) {
		model.addAttribute("req", request);
		return "login";
	}

	@GetMapping("/access-denied.html")
	public String displayAccessDeniedPage() {
		return "access-denied";
	}

}
