package mastersquirrel.entities;

public enum EntityType {
    GOODPLANT("GoodPlant"), BADPLANT("BadPlant"), GOODBEAST("GoodBeast"), BADBEAST("BadBeast"), WALL("Wall"), EMPTY("Empty");

    private String type;


    EntityType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}