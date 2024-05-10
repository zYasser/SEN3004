package sen3004.app7.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sen3004.app7.dao.App7AuthorRepository;
import sen3004.app7.dao.App7BookRepository;
import sen3004.app7.model.Author;
import sen3004.app7.model.Book;

@Service
@Transactional
public class App7Service {

	@Autowired
	private App7AuthorRepository authorRepository;

	@Autowired
	private App7BookRepository bookRepository;

	public Author findAuthorById(long id) {
		return authorRepository.findById(id).get();
	}
	
	public List<Author> findAuthorByName(String name) {
		return authorRepository.findByFirstName(name);
//		return authorRepository.findByFirstNameStartingWith(name);
//		return authorRepository.findByFirstNameEndingWith(name);
//		return authorRepository.findByFirstNameContaining(name);
//		return authorRepository.findByIdLessThanOrFirstName(3L, name);
//		return authorRepository.findByQuery(name);
	}

	public Book findBookById(long id) {
		return bookRepository.findById(id).get();
	}
	
	public Page<Book> findBooks(Pageable pageRequest){
		return bookRepository.findAll(pageRequest);
	}
	
	public void saveBook(Book book) {
		bookRepository.save(book);
	}
	
	public void saveAuthor(Author author) {
		authorRepository.save(author);
	}
}
