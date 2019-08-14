
package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetStatus extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Controller.setSendJson();
        Person person = (Person) request.getSession().getAttribute("user");
        if(person.getStatus() != null){

            return statusToJSON(person.getStatus());
        }else {
            return "No_status";
        }
    }


}