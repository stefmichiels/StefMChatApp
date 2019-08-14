package domain;

import db.PersonRepository;
import db.PersonRepositoryStub;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
	private PersonRepository personRepository = new PersonRepositoryStub();
	public ArrayList<Chat> chatSessions = new ArrayList<>();

	public PersonService(){
	}

	public Person getPerson(String personId)  {
		return getPersonRepository().get(personId);
	}



	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePerson(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}

	public Person getValidUser(String email, String password) {
		return getPersonRepository().getValidUser(email, password);
	}

	public void changeStatus(String status, Person person){
		person.setStatus(status);
		getPersonRepository().update(person);
	}

	public String getStatus(Person person){
		return getPersonRepository().get(person.getUserId()).getStatus();
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}


}