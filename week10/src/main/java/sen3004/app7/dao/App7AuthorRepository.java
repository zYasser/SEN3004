package sen3004.app7.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sen3004.app7.model.Author;

public interface App7AuthorRepository extends JpaRepository<Author, Long> {

	public List<Author> findByFirstName(String firstName);

	public List<Author> findByFirstNameStartingWith(String text);

	public List<Author> findByFirstNameEndingWith(String text);

	public List<Author> findByFirstNameContaining(String text);

	public List<Author> findByIdLessThan(Long value);

	public List<Author> findByIdLessThanOrFirstName(Long value, String firstName);

	@Query(value = "select * from author where lower(lname) = lower(:lastName)", nativeQuery = true)
	public List<Author> findByQuery(@Param("lastName") String lastName);

}
