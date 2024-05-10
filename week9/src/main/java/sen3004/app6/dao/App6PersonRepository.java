package sen3004.app6.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import sen3004.app6.model.Person;

@Repository
public class App6PersonRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findAll() {
		return entityManager.createQuery("from Person", Person.class).getResultList();
	}

	public Person findById(long id) {
		return entityManager.find(Person.class, id);
	}

	public List<Person> findByLastName(String lastName) {
		return entityManager.createQuery("from Person where lastName = :last", Person.class)
				            .setParameter("last", lastName).getResultList();
	}

	public void create(Person person) {
		entityManager.persist(person);
	}

	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Person.class, id));
	}

	public void update(Person person) {
		entityManager.merge(person);
	}

}
