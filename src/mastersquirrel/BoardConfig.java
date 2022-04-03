package mastersquirrel;

public class BoardConfig {
    public final XY size;
    public final int wallCount;


    public BoardConfig(XY size, int wallCount) {
        this.size = size;
        this.wallCount = wallCount;
    }
}