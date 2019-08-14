package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;
import domain.Role;

public class PersonRepositoryStub implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();

	public PersonRepositoryStub () {
		Person stef = new Person("stef@ucll.be", "t", "Stef", "Michiels", Role.BIB);
		stef.setStatus("Offline");
		add(stef);
		Person tine = new Person("tine@ucll.be", "t", "Tine", "Keulemans", Role.LID);
		tine.setStatus("Offline");
		tine.addFriend(stef);
		add(tine);
		Person erika = new Person("erika@ucll.be", "t", "Erika", "Peeters", Role.LID);
		erika.setStatus("Offline");
		erika.addFriend(stef);
		erika.addFriend(tine);
		add(erika);
	}

	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id");
		}
		return persons.get(personId);
	}

	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());
	}

	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person");
		}
		persons.put(person.getUserId(), person);
	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person");
		}
		if (persons.containsKey(person.getUserId())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUserId(), person);
	}



	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id");
		}
		persons.remove(personId);
	}

	public Person getValidUser(String email, String password) {
		Person person = get(email);

		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}
}