public class Main {
    public static void main(String[] args) {

        GoodBeast goodBeast1 = new GoodBeast();
        BadBeast badBeast1 = new BadBeast();
        BadPlant badPlant1 = new BadPlant();
        GoodPlant goodPlant1 = new GoodPlant();
        MasterSquirrel masterSquirrel1 = new MasterSquirrel();
        HandOperatedMasterSquirrel playerSquirrel = new HandOperatedMasterSquirrel();
        Wall wall1 = new Wall();

        EntityList entityList = EntityList.getInstance();

        while(true){
            System.out.println(entityList.listToString());
            entityList.nextStep();
            //playerSquirrel.spawnMiniSquirrel(0);
        }
    }
}
