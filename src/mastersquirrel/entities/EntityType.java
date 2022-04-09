package mastersquirrel.entities;

public enum EntityType {
    GOODPLANT("GoodPlant"),
    BADPLANT("BadPlant"),
    GOODBEAST("GoodBeast"),
    BADBEAST("BadBeast"),
    WALL("Wall"),
    EMPTY("Empty"),
    MINISQUIRREL("MiniSquirrel"),
    MASTERSQUIRREL("MasterSquirrel"),
    HANDOPERATEDMASTERSQUIRREL("HandOperatedMasterSquirrel");

    private final String type;


    EntityType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}