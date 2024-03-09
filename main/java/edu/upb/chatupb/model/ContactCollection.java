package edu.upb.chatupb.model;

import edu.upb.chatupb.server.objects.Contacto;

import java.util.ArrayList;

public class ContactCollection implements Iterador {
    private ArrayList list = new ArrayList<>();
    private int index = 0;

    public void add(Contacto contact) {
        list.add(contact);
    }



    @Override
    public Boolean hasNext() {
        return index < list.size();
    }

    @Override
    public  Object getnext() {
        if (!hasNext()) {
            return null;
        }
        Object obj = list.get(index);
        index++;
        return obj;
    }


}
