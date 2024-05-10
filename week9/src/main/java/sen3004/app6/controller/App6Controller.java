package sen3004.app6.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import sen3004.app6.model.Person;
import sen3004.app6.model.Phone;
import sen3004.app6.service.App6Service;

@Controller
public class App6Controller {

	@Autowired
	private App6Service service;

	@GetMapping({ "/person/new", "new_person.html" })
	public ModelAndView newPerson() {
		ModelAndView mv = new ModelAndView("new-person");
		mv.addObject("person", new Person());

		return mv;
	}

	@PostMapping("/person/add")
	public ModelAndView addPerson(@Valid @ModelAttribute Person person, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);

		if (result.hasErrors())
			mv.setViewName("new-person");
		else {
			mv.setViewName("person-list");
			service.createPerson(person);
			mv.addObject("persons", service.findAllPersons());
		}

		return mv;
	}

	@GetMapping({ "/list-persons", "list.html" })
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("person-list");
		mv.addObject("persons", service.findAllPersons());

		return mv;
	}
	
	@GetMapping("/find-person/{name}")
	public ModelAndView listByName(@PathVariable String name) {
		ModelAndView mv = new ModelAndView("person-list");
		mv.addObject("persons", service.findPersonByLastName(name));

		return mv;
	}

	@GetMapping("/person/delete/{id}")
	public ModelAndView deletePerson(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("person-list");
		service.deletePerson(id);
		mv.addObject("persons", service.findAllPersons());

		return mv;
	}

	@GetMapping({ "/person/view/{id}", "/person/{id}" })
	public ModelAndView viewPerson(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("view-person");
		Person person = service.findPersonById(id);
		mv.addObject("person", person);
		mv.addObject("phone", new Phone(person));

		return mv;
	}

	@PostMapping("/person/phone/add")
	public ModelAndView addPhoneToPerson(@Valid @ModelAttribute Phone phone, BindingResult result) {
		ModelAndView mv = new ModelAndView("view-person");
		Person person = service.findPersonById(phone.getPerson().getId());
		
		if (result.hasErrors() == false) {
			service.createPhone(phone);
			mv.addObject("phone", new Phone(person));
		} 
		else {
			mv.addObject("phone", phone);
		}

		mv.addObject("person", person);

		return mv;
	}

	@GetMapping("/person/phone/delete/{pid}/{phid}")
	public ModelAndView deletePhone(@PathVariable long pid, @PathVariable long phid) {
		ModelAndView mv = new ModelAndView("view-person");
		service.deletePhone(phid);
		Person person = service.findPersonById(pid);
		mv.addObject("person", person);
		mv.addObject("phone", new Phone(person));

		return mv;
	}

}
