package sen3004.app6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "phone_number")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 10, max = 10)
	private String number;

	@ManyToOne
	@JoinColumn(name = "personid")
	private Person person;

	public Phone() {
		
	}
	
	public Phone(Person person) {
		this.person = person;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
