package mastersquirrel.entities;

import javafx.scene.paint.Color;

public enum EntityType {
    GOODPLANT("GoodPlant",Color.DARKGREEN),
    BADPLANT("BadPlant",Color.GREENYELLOW),
    GOODBEAST("GoodBeast",Color.LIGHTSKYBLUE),
    BADBEAST("BadBeast",Color.MEDIUMVIOLETRED),
    WALL("Wall",Color.DARKGREY),
    EMPTY("Empty",Color.LIGHTGREY),
    MINISQUIRREL("MiniSquirrel",Color.HOTPINK),
    MASTERSQUIRREL("MasterSquirrel",Color.VIOLET),
    HANDOPERATEDMASTERSQUIRREL("HandOperatedMasterSquirrel",Color.BLUEVIOLET);

    private final String type;
    private final Color color;

    EntityType(String type, Color color) {
        this.type = type;
        this.color = color;
    }

    public String getType(){
        return type;
    }
    public Color getColor() { return color; }
}