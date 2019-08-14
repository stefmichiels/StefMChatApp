package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFriends extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Controller.setSendJson();
        String friendString = request.getParameter("friend");
        Person friend = getPersonService().getPerson(friendString);
        Person person = (Person) request.getSession().getAttribute("user");
        if (person.getFriends().contains(friend)) {
            throw new IllegalArgumentException("Already friends");
        }
        else {
            person.addFriend(friend);
        }
        response.setContentType("application/json");
        return this.toJSON(getPersonService().getPersons());
    }
}