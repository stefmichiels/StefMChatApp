package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetFriends extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person user = (Person) request.getSession().getAttribute("user");
        Controller.setSendJson();
        String jsonList = "";
        if (user != null){
            List<Person> friendslist = user.getFriends();
            jsonList = listToJSON(friendslist);
        }
        response.setContentType("application/json");
        return jsonList;
    }


}