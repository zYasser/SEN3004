package sen3004.app9a.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import sen3004.app9a.model.Person;
import sen3004.app9a.service.App9aService;

@RestController
@RequestMapping("/rest")
public class App9aRestController {

	@Autowired
	private App9aService service;

	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> persons = service.findAll();

		return ResponseEntity.ok(persons);
	}

	@GetMapping("/person")
	public ResponseEntity<List<Person>> getPersons(@RequestParam String last) {
		List<Person> persons = service.findByLastName(last);

		return ResponseEntity.ok(persons);
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Long id) {
		Person person = service.findById(id);

		if (person != null)
			return ResponseEntity.ok(person);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("/person")
	public ResponseEntity<URI> createPerson(@RequestBody Person person) {
		try {
			service.create(person);		// An ID value is created while inserting person data into the database.
			Long id = person.getId();
			URI location = ServletUriComponentsBuilder
					       .fromCurrentRequest()
					       .path("/{id}")
					       .buildAndExpand(id)
					       .toUri();

			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(value = "/person/{id}")
	public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody Person person) {
		try {
			Person personFromDb = service.findById(id);
			personFromDb.setFirstName(person.getFirstName());
			personFromDb.setLastName(person.getLastName());
			personFromDb.setPhoneNumber(person.getPhoneNumber());
			service.update(personFromDb);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(value = "/person/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
		try {
			service.delete(id);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
