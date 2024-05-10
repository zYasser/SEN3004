package sen3004.app8.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sen3004.app8.model.Person;

@Controller
public class App8Controller {

	@Autowired
	MessageSource messageSource;

	@GetMapping("/page1.html")
	public String displayPage1() {
		return "page1";
	}

	@GetMapping("/page2.html")
	public String displayPage2(Model model) {
		model.addAttribute("name", "David");
		return "page2";
	}

	@GetMapping("/show-message")
	@ResponseBody
	public String showMessage() {
		return messageSource.getMessage("someMessage", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/greet")
	@ResponseBody
	public String greet() {
		String[] args = new String[] { "Alison", "Eric" };
		return messageSource.getMessage("greet", args, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/form.html")
	public ModelAndView displayForm() {
		ModelAndView mv = new ModelAndView("form");
		mv.addObject("person", new Person());

		return mv;
	}

	@PostMapping("/send-form-data")
	public ModelAndView processForm(@Valid @ModelAttribute Person person, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);

		if (result.hasErrors())
			mv.setViewName("form");
		else
			mv.setViewName("form-result");

		return mv;
	}

}
