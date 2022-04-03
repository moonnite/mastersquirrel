package mastersquirrel;

public class BoardConfig {
    public final XY size;
    public final int wallCount;
    public final int goodBeastCount;
    public final int badBeastCount;
    public final int goodPlantCount;
    public final int badPlantCount;


    public BoardConfig(XY size, int wallCount) {
        this.size = size;
        this.wallCount = wallCount;
        this.goodBeastCount = goodBeastCount;
        this.badBeastCount = badBeastCount;
        this.goodPlantCount = goodPlantCount;
        this.badPlantCount = badPlantCount;
    }
}