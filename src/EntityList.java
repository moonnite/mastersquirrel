public class EntityList {
    private static final EntityList entityList;
    private EntityElement first;

    private EntityList(){
        first = null;
    }

    static {
        entityList = new EntityList();
    }

    public static EntityList getInstance(){
        return entityList;
    }

    public AEntity pull(int ID){
        if(first == null) return null;
        EntityElement current = first;
        do{
            if(current.getData().getID() == ID){
                if(first==current) first = current.getNext();
                AEntity temp = current.getData();
                if(current.getPrev() != null) current.getPrev().setNext(current.getNext());
                if(current.getNext() != null) current.getNext().setPrev(current.getPrev());
                return temp;
            }
            current = current.getNext();
            if(current == null) return null;
        } while(current.getNext()!=null);
        return null;
    }

    public AEntity get(int ID){
        if(first == null) return null;
        EntityElement current = first;
        do{
            if(current.getData().getID() == ID){
                return current.getData();
            }
            current = current.getNext();
            if(current == null) return null;
        } while(current.getNext()!=null);
        return null;
    }

    public void put (AEntity e){
        if(get(e.getID()) != null) return;
        if(first == null) {
            first = new EntityElement(e);
            return;
        }
        EntityElement current = first;
        while(current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(new EntityElement(e));
        current.getNext().setPrev(current);
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

    public void nextStep(){
        if(first == null) return;
        EntityElement current = first;
        do {
            current.getData().nextStep();
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
