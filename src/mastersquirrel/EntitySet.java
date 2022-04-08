package mastersquirrel;

import mastersquirrel.entities.AEntity;

import java.util.*;
import java.util.stream.IntStream;

public class EntitySet {
    private static final EntitySet ENTITY_SET = new EntitySet();
    private EntityElement first;

    private EntitySet(){
        first = null;
    }

    public static EntitySet getInstance(){
        return ENTITY_SET;
    }

    //throws unchecked exception when element does not exist in list
    public AEntity pull(int ID){
        if(first == null){
            throw new ElementNotInListException("Element not in List");
        }

        if(get(ID) == null) {
            throw new ElementNotInListException("Element not in List");
        }

        EntityElement current = first;
        if (current.getData().getID() == ID){
            first = current.getNext();
            return current.getData();
        }
        while(current != null) {
            if (current.getData().getID() == ID) {
                AEntity temp = current.getData();
                if (current.getPrev() != null) current.getPrev().setNext(current.getNext());
                if (current.getNext() != null) current.getNext().setPrev(current.getPrev());
                return temp;
            }
            current = current.getNext();
        }
        return null;
    }

    public AEntity get(int ID){
        if(first == null) return null;
        EntityElement current = first;
        while(current != null){
            if(current.getData().getID() == ID) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    //throws unchecked exception when element already in list
    public void put (AEntity e){
        if(get(e.getID()) != null){
            throw new ElementAlreadyExistsException("Element already in List");
        }

        EntityElement current = new EntityElement(e);

        if(first == null) {
            first = current;
            last = first;
            return;
        }

        last.setNext(current);
        current.setPrev(last);
        last = current;
    }

    public int length(){
        if(first == null) return 0;
        int l = 1;
        EntityElement current = first;
        while(current.getNext()!=null){
            current = current.getNext();
            l++;
        }
        return l;
    }

    public String listToString(){
        StringBuilder s = new StringBuilder();
        if(first == null) return s.toString();
        EntityElement current = first;
        do {
            s.append("\n").append(current.getData().toString());
            current = current.getNext();
        } while(current != null);

        return s.toString();
    }

    public AEntity[] getAll(){
        if(first == null) return null;
        AEntity[] entities = new AEntity[length()];

        EntityElement current = first;
        for(int i = 0; current != null; i++){
            entities[i] = current.getData();
            current = current.getNext();
        }
        return entities;
    }

    public void nextStep(EntityContext entityContext){
        if(first == null) return;
        EntityElement current = first;
        do {
            current.getData().nextStep(entityContext);
            current = current.getNext();
        } while(current != null);
    }
}


class EntityElement{

    private final AEntity data;
    private EntityElement prev;
    private EntityElement next;

    EntityElement(AEntity d){
        data = d;
    }

    public EntityElement getNext() { return next; }
    public EntityElement getPrev() { return prev; }

    public void setNext(EntityElement next) { this.next = next;}
    public void setPrev(EntityElement prev) { this.prev = prev;}

    public AEntity getData(){
        return data;
    }
}