
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

public class UpdateLastName extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastname");
        Person user = (Person)request.getSession().getAttribute("user");
        response.setHeader("Access-Control-Allow-Origin", "*");

        if(user != null ){
            getPersonService().getPerson(user.getUserId()).setLastName(lastName);
            Controller.setSendJson();
            return toJSON(user.getLastName());
        }else{
            getPersonService().getPerson(request.getParameter("userId")).setLastName(request.getParameter("lastName"));
            return toJSON(user.getLastName());
        }
    }






}