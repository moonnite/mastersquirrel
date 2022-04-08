package mastersquirrel;

import mastersquirrel.entities.AEntity;

import java.util.*;
import java.util.stream.IntStream;

public class EntitySet {
    private static final EntitySet ENTITY_SET = new EntitySet();
    private EntityElement first;
    private EntityElement last;

    private EntitySet(){
        first = null;
        last = null;
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
                if (current == last) last = current.getPrev();
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

    //anonymous class
    public Enumeration enumerateForward(){
        return new Enumeration() {
            EntityElement current = first;
            @Override
            public boolean hasMoreElements() {
                return (current.getNext() != null);
            }
            @Override
            public Object nextElement() {
                EntityElement temp = current.getNext();
                current = current.getNext();
                return temp.getData();
            }
        };
    }

    //local class
    public Enumeration enumerateBackward(){
        class Enumeration implements java.util.Enumeration {
            EntityElement current = last;
            @Override
            public boolean hasMoreElements() {
                return (current.getPrev()!=null);
            }
            @Override
            public Object nextElement() {
                EntityElement temp = current.getPrev();
                current = current.getPrev();
                return temp.getData();
            }
        }
        return new Enumeration();
    }

    public Enumeration enumerateRandom(){
        return new EnumerationRandom();
    }

    //inner class
    private class EnumerationRandom implements java.util.Enumeration {
        AEntity[] aEntities = getAll();
        int[] range = IntStream.rangeClosed(0, aEntities.length-1).toArray();
        ArrayList<Integer> arrayList = new ArrayList<>();

        public EnumerationRandom(){
            for(Integer i:range){arrayList.add(i);}
            Collections.shuffle(arrayList, new Random(42069));
        }
        @Override
        public boolean hasMoreElements() {
            return !arrayList.isEmpty();
        }
        @Override
        public Object nextElement() {
            AEntity temp = aEntities[arrayList.get(0)];
            arrayList.remove(0);
            return temp;
        }
    }

    private static class EntityElement{

        private final AEntity data;
        private EntityElement prev;
        private EntityElement next;

        private EntityElement(AEntity d){ data = d; }

        private EntityElement getNext() { return next; }
        private EntityElement getPrev() { return prev; }

        private void setNext(EntityElement next) { this.next = next;}
        private void setPrev(EntityElement prev) { this.prev = prev;}

        private AEntity getData(){return data;}
    }
}
