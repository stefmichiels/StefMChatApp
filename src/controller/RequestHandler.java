package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonService;
import domain.Person;
import domain.Role;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public abstract class RequestHandler {
	
	private PersonService personService;

	public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
	public void setModel (PersonService personService) {
		this.personService = personService;
	}

	public PersonService getPersonService() {
		return personService;
	}
	
	protected boolean isFromUserWithRole (HttpServletRequest request, Role role) {
		Person person = (Person) request.getSession().getAttribute("user");
		if (person != null && person.getRole().equals(role)) {
			return true;
		}
		return false;
	}
	public String toJSON (Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String statusToJSON (String status) {
		StringBuffer json = new StringBuffer();

		json.append("{ \"status\" : \"");
		json.append(status);
		json.append("\"}");

		return json.toString();
	}




	public String listToJSON(List<Person> friendslist){
		String json = "[";
		int i = 0;
		for (Person friend : friendslist){
			json += "{\"userId\":\"" + friend.getUserId() + "\"," +
					"\"firstName\":\"" + friend.getFirstName() + "\"," +
					"\"lastName\":\"" + friend.getLastName() + "\"," +
					"\"status\":\"" + friend.getStatus() + "\"}";
			i++;
			if (friendslist.size() != i){
				json += ",";
			}
		}
		json += "]";
		return json;
	}
}
