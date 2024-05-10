package sen3004.app6.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sen3004.app6.dao.App6PersonRepository;
import sen3004.app6.dao.App6PhoneRepository;
import sen3004.app6.model.Person;
import sen3004.app6.model.Phone;

@Service
@Transactional
public class App6Service {

	@Autowired
	private App6PersonRepository personRepository;

	@Autowired
	private App6PhoneRepository phoneRepository;

	public List<Person> findAllPersons() {
		return personRepository.findAll();
	}
	
	public Person findPersonById(long id) {
		return personRepository.findById(id);
	}
	
	public List<Person> findPersonByLastName(String lastName) {
		return personRepository.findByLastName(lastName);
	}

	public void createPerson(Person person) {
		personRepository.create(person);
	}

	public void createPhone(Phone phone) {
		phoneRepository.create(phone);
	}

	public void deletePerson(long id) {
		phoneRepository.deleteByPersonId(id);
		personRepository.delete(id);
	}

	public void deletePhone(long id) {
		phoneRepository.delete(id);
	}

}
