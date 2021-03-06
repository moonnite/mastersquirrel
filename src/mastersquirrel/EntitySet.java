package mastersquirrel;

import mastersquirrel.exeptions.ElementAlreadyExistsException;
import mastersquirrel.exeptions.ElementNotInListException;
import mastersquirrel.entities.AEntity;

import java.util.*;
import java.util.stream.IntStream;

public class EntitySet implements Collection{
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

    public void clear(){
        first = null;
        last = null;
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
    public boolean put (AEntity e){
        if(get(e.getID()) != null){
            throw new ElementAlreadyExistsException("Element already in List");
        }

        EntityElement current = new EntityElement(e);

        if(first == null) {
            first = current;
            last = first;
            return false;
        }

        last.setNext(current);
        current.setPrev(last);
        last = current;
        return true;
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
        Iterator iterator = enumerateRandom();
        while(iterator.hasNext()){
            AEntity a = (AEntity)iterator.next();
            //System.out.println(a.getID());
            a.nextStep(entityContext);
        }
        //System.out.println("Updated "+count+" entities.");
    }

    //anonymous class
    public Iterator iterateForward(){
        return new Iterator() {
            EntityElement current = first;

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public Object next() {
                EntityElement temp = current;
                current = current.getNext();
                return temp.getData();
            }
        };
    }

    //local class
    public Iterator enumerateBackward(){
        class Iteration implements java.util.Iterator {
            EntityElement current = last;

            @Override
            public boolean hasNext() {
                return (current!=null);

            }

            @Override
            public Object next() {
                EntityElement temp = current;
                current = current.getPrev();
                return temp.getData();
            }
        }
        return new Iteration();
    }

    public Iterator enumerateRandom(int seed){
        return new IterateRandom(seed);
    }

    public Iterator enumerateRandom(){
        return new IterateRandom();
    }

    //inner class
    private class IterateRandom implements java.util.Iterator {
        AEntity[] aEntities = getAll();
        int[] range = IntStream.rangeClosed(0, aEntities.length-1).toArray();
        ArrayList<Integer> arrayList = new ArrayList<>();

        public IterateRandom(int seed){
            for(Integer i:range){arrayList.add(i);}
            Collections.shuffle(arrayList, new Random(seed));
        }

        public IterateRandom(){
            for(Integer i:range){arrayList.add(i);}
            Collections.shuffle(arrayList, new Random());
        }

        @Override
        public boolean hasNext() {
            return !arrayList.isEmpty();
        }

        @Override
        public Object next() {
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

    // Collection
    @Override
    public int size() {
        return length();
    }

    @Override
    public boolean isEmpty() {
        return length()<1;
    }

    @Override
    public boolean contains(Object o) {
        AEntity aEntity;
        try {
            aEntity = (AEntity) o;
        }
        catch (Exception e){
            return false;
        }
        return (get(aEntity.getID()) != null);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return getAll();
    }

    @Override
    public boolean add(Object o) {
        AEntity aEntity;
        try {
            aEntity = (AEntity) o;
        }
        catch (Exception e){
            return false;
        }
        return put(aEntity);
    }

    @Override
    public boolean remove(Object o) {
        AEntity aEntity;
        try {
            aEntity = (AEntity) o;
        }
        catch (Exception e){
            return false;
        }
        return (pull(aEntity.getID()) != null);
    }

    // not suported

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
