package sen3004.app9b.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sen3004.app9b.model.Person;

@Controller
public class App9bController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/fetch-persons")
	@ResponseBody
	public List<Person> fetchPersons() {
		String url = "http://localhost:8080/rest/persons";
		ResponseEntity<Person[]> response = restTemplate.getForEntity(url, Person[].class);
		
		return Arrays.asList(response.getBody());
	}

	@GetMapping("/fetch-persons/{last}")
	@ResponseBody
	public List<Person> fetchPersonsByLastName(@PathVariable String last) {
		String url = "http://localhost:8080/rest/person";
		String urlWithLastName = UriComponentsBuilder
				                 .fromHttpUrl(url)
				                 .queryParam("last", last)
				                 .toUriString();
		ResponseEntity<Person[]> response = restTemplate.getForEntity(urlWithLastName, Person[].class);
		
		return Arrays.asList(response.getBody());
	}

	@GetMapping("/create-person/{first}/{last}")
	@ResponseBody
	public String createPerson(@PathVariable String first, @PathVariable String last) {
		String url = "http://localhost:8080/rest/person";
		Person p = new Person();
		p.setFirstName(first);
		p.setLastName(last);
		URI location = restTemplate.postForLocation(url, p);
		
		return location.toString();
	}

	@GetMapping("/update-person/{id}/{last}")
	@ResponseBody
	public String updatePerson(@PathVariable Long id, @PathVariable String last) {
		String url = "http://localhost:8080/rest/person/{id}";
		String urlWithId = UriComponentsBuilder
				           .fromHttpUrl(url)
				           .buildAndExpand(id)
				           .toUriString();
		
		Person p = restTemplate.getForObject(urlWithId, Person.class);
		p.setLastName(last);	
		restTemplate.put(urlWithId, p);
		
		return "Person with ID " + id + " updated.";
	}

	@GetMapping("/delete-person/{id}")
	@ResponseBody
	public String deletePersonById(@PathVariable Long id) {
		String url = "http://localhost:8080/rest/person/{id}";
		String urlWithId = UriComponentsBuilder
				           .fromHttpUrl(url)
				           .buildAndExpand(id)
				           .toUriString();
		
		restTemplate.delete(urlWithId);
		
		return "Person with ID " + id + " deleted.";
	}
}
