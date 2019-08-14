
package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.DomainException;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateFirstName extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        Person user = (Person)request.getSession().getAttribute("user");
        response.setHeader("Access-Control-Allow-Origin", "*");

        if(user != null ){
            getPersonService().getPerson(user.getUserId()).setFirstName(firstName);
            Controller.setSendJson();
            return toJSON(user.getFirstName());
        }else{
            getPersonService().getPerson(request.getParameter("userId")).setFirstName(request.getParameter("firstName"));
            return toJSON(user.getFirstName());
        }
    }







}