package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Person;
import domain.PersonService;

public class LogIn extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<String>();
		
		String email = request.getParameter("email");
		if (email == null || email.isEmpty()) {
			errors.add("No email");
		}
		
		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add("No password");
		}
		
		if (errors.size() == 0) {
			PersonService personService = getPersonService();
			Person person = personService.getValidUser(email, password);
			person.setStatus("Online");
			if (person != null) {
				createSession(person, request, response);
			} else {
				errors.add("invalid info");
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}

		Controller.sendHtml();
		return destination;	
	}
	
	private void createSession(Person person, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("user", person);
	}

}
