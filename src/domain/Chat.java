package domain;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private Person correspondent1;
    private Person correspondent2;
    public List<String> berichten;

    public Chat(Person c1, Person c2){
        this.correspondent1 = c1;
        this.correspondent2=c2;
        berichten= new ArrayList<>();
    }

    public Person getCorrespondent1() {
        return correspondent1;
    }

    public Person getCorrespondent2() {
        return correspondent2;
    }

    public List<String> getBerichten() {
        return berichten;
    }
}
