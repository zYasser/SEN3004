package sen3004.app7.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sen3004.app7.model.Book;

public interface App7BookRepository extends JpaRepository<Book, Long> {

}
