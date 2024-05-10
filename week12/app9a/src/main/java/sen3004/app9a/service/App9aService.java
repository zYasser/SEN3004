package sen3004.app9a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sen3004.app9a.dao.App9aRepository;
import sen3004.app9a.model.Person;

@Service
@Transactional
public class App9aService {

	@Autowired
	private App9aRepository repository;
	
	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person findById(long id) {
		return repository.findById(id).get();
	}

	public List<Person> findByLastName(String lastName) {
		return repository.findByLastNameIgnoreCase(lastName);
	}

	public void update(Person person) {
		repository.save(person);
	}

	public void create(Person person) {
		repository.save(person);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
