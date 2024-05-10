package sen3004.app7.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 3, max = 50)
	@Column(name = "fname")
	private String firstName;

	@Size(min = 3, max = 50)
	@Column(name = "lname")
	private String lastName;

	@ManyToMany
	@JoinTable(name = "auth_book", 
	           joinColumns = @JoinColumn(name = "aid"),
	           inverseJoinColumns = @JoinColumn(name = "bid"))
	@OrderBy(value = "id")
	private Set<Book> books = new HashSet<>();

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
