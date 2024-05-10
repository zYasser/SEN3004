package sen3004.app9a.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sen3004.app9a.model.Person;

public interface App9aRepository extends JpaRepository<Person, Long> {

	public List<Person> findByLastNameIgnoreCase(String lastName);
	
}
