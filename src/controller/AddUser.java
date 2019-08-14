package controller;

import domain.Person;
import domain.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "index.jsp";
        String firstName = request.getParameter("firstName");
        String lastName =request.getParameter("lastName");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordAgain");

        String email =request.getParameter("email");



        Person person = new Person();

        if ( !passwordRepeat.isEmpty() && !password.isEmpty() && !email.isEmpty() ){
            if (password.equals(passwordRepeat)){
                person.setPassword(password);
                person.setHashedPassword(password);
                person.setRole(Role.LID);
                person.setStatus("New user :)");
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setUserId(email);
                getPersonService().addPerson(person);
            }else {
                throw new IllegalArgumentException("Wrong password");
            }
        }else {
            throw new IllegalArgumentException("Empty field");
        }
        return destination;
    }
}