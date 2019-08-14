package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetOnline extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person user = (Person) request.getSession().getAttribute("user");
        int online = 0;
        int offline=0;
        Controller.setSendJson();
        if(user != null){
            List<Person> friendslist = user.getFriends();
            for(Person p: friendslist){
                if(!p.getStatus().equals("Offline")){
                    online++;
                }
                else{
                    offline++;
                }
            }

        }
        return "[{" +  "\"online\":\"" + online + "\"," +
                "\"offline\":\"" + offline + "\"}]";
    }
}
