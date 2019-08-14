package controller;

import domain.Chat;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendMessage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("user");

        if(user != null){
            String userid = request.getParameter("userId");
            Person p2 = getPersonService().getPerson(userid);

            Chat correctSession = null;

            if(user.getFriends().contains(getPersonService().getPerson(userid))){
                for(Chat c : this.getPersonService().chatSessions){
                    if((c.getCorrespondent1() .getUserId().equals(user.getUserId()) && c.getCorrespondent2().getUserId().equals(userid)) ||
                            (c.getCorrespondent2().getUserId().equals(user.getUserId()) && c.getCorrespondent1().getUserId().equals(userid))){
                        correctSession = c;
                        break;
                    }
                }
                if(correctSession == null){
                    correctSession = new Chat(user, getPersonService().getPerson(userid));
                    this.getPersonService().chatSessions.add(correctSession);
                }

                if(!request.getParameter("message").isEmpty()){
                    correctSession.getBerichten().add(user.getUserId()+": " + request.getParameter("message"));

                }
            }
        }
        response.setContentType("application/json");
        return null;
    }

}
