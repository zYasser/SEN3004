package sen3004.app6.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import sen3004.app6.model.Phone;

@Repository
public class App6PhoneRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Phone> findAll() {
		return entityManager.createQuery("from Phone", Phone.class).getResultList();
	}

	public Phone findById(long id) {
		return entityManager.find(Phone.class, id);
	}

	public List<Phone> findByPersonId(long id) {
		return entityManager.createQuery("from Phone where person.id = :id", Phone.class)
				            .setParameter("id", id).getResultList();
	}

	public void create(Phone phone) {
		entityManager.persist(phone);
	}

	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Phone.class, id));
	}

	public void deleteByPersonId(long id) {
		entityManager.createQuery("delete from Phone where person.id = :id")
		             .setParameter("id", id).executeUpdate();
	}

}
