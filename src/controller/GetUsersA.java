
package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersA extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller.setSendJson();
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setContentType("application/json");
        return setUsersJson(getPersonService().getPersons());
    }

    public String setUsersJson(List<Person> persons) {
        String json = "[";
        int i = 0;
        for (Person p : persons) {
            json += "{\"id\": \"" + p.getUserId() + "\", " +
                    "\"firstname\": \"" + p.getFirstName() + "\"," +
                    "\"lastname\": \"" + p.getLastName() + "\"," +
                    "\"status\": \"" + p.getStatus() + "\"}";
            i++;
            if (persons.size() != i) {
                json += ",";
            }
        }
        json += "]";
        return json;
    }
}