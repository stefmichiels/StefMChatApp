package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Chat;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetChat extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("user");
        Controller.setSendJson();
        if(user != null){
            String userid = request.getParameter("userId");
            Chat correctSession = null;

            if(user.getFriends().contains(getPersonService().getPerson(userid))){
                for(Chat c : this.getPersonService().chatSessions){
                    if((c.getCorrespondent1().getUserId().equals(user.getUserId()) && c.getCorrespondent2().getUserId().equals(userid)) ||
                            (c.getCorrespondent2().getUserId().equals(user.getUserId()) && c.getCorrespondent1().getUserId().equals(userid))){
                        correctSession = c;
                        break;
                    }
                }
                if(correctSession == null){
                    correctSession = new Chat(user, getPersonService().getPerson(userid));
                    this.getPersonService().chatSessions.add(correctSession);
                }


            }
            response.setContentType("application/json");
            return ListToJSON(correctSession.getBerichten());
        }
        return "";
    }

    public String ListToJSON(List list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}


