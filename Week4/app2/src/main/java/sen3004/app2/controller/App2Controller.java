package sen3004.app2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class App2Controller {

	@GetMapping(value = "/sen3004")
	@ResponseBody
	public String requestHandler1() {
		return "Web Programming";
	}

	@GetMapping("/wp")
	@ResponseBody
	public String requestHandler2() {
		return "Web Programming"; // "<h2>Web Programming</h2>"
	}

	@GetMapping("/app2/{val}")
	@ResponseBody
	public String requestHandler3(@PathVariable("val") String value) {
		return value;
	}

	@GetMapping("/sum/{val1}/{val2}")
	@ResponseBody
	public String sum(@PathVariable int val1, @PathVariable int val2) {
		return String.format("%d + %d = %d", val1, val2, val1 + val2);
	}

	@GetMapping("/sum2")
	@ResponseBody
	public String sum2(@RequestParam("input1") int val1, @RequestParam("input2") int val2) {
		return String.format("%d + %d = %d", val1, val2, val1 + val2);
	}

	@GetMapping("/sum3")
	@ResponseBody
	public String sum3(@RequestParam int val1, @RequestParam int val2) {
		return String.format("%d + %d = %d", val1, val2, val1 + val2);
	}

	@GetMapping("/echo")
	@ResponseBody
	public String echo(@RequestParam(value = "input", required = false) String val) {
		return val;
	}

	@GetMapping("/greet/{name}")
	public String greetUser(Model model, @PathVariable String name) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd.MM.yyyy - HH:mm", Locale.ROOT);
		model.addAttribute("user", name);
		model.addAttribute("date", now.format(formatter));
		model.addAttribute("handler", "greetUser");
		return "greeting"; // points to "/src/main/resources/templates/greeting.html".
	}

	@GetMapping("/calculate/{input1}/{input2}")
	public ModelAndView calculator(@PathVariable int input1, @PathVariable int input2) {
		ModelAndView mv = new ModelAndView("calculator"); // points to "/src/main/resources/templates/calculator.html".
		mv.addObject("input1", input1);
		mv.addObject("input2", input2);
		mv.addObject("sum", input1 + input2);
		mv.addObject("sub", input1 - input2);
		mv.addObject("mul", input1 * input2);
//		mv.addObject("dv", (double) input1 / input2);
		mv.addObject("dv", String.format(Locale.ROOT, "%.2f", (double) input1 / input2));
		return mv;
	}
}
