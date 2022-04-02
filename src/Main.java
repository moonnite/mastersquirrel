public class Main {
    public static void main(String[] args){

        GoodBeast goodBeast1 = new GoodBeast();
        BadBeast badBeast1 = new BadBeast();
        BadPlant badPlant1 = new BadPlant();
        GoodPlant goodPlant1 = new GoodPlant();
        MasterSquirrel masterSquirrel1 = new MasterSquirrel();
        HandOperatedMasterSquirrel playerSquirrel = new HandOperatedMasterSquirrel();
        Wall wall1 = new Wall();

        EntitySet entitySet = EntitySet.getInstance();
        entitySet.put(goodBeast1);
        entitySet.pull(2);
        entitySet.pull(2);

        while(true){
            System.out.println(entitySet.listToString());
            entitySet.nextStep();
            playerSquirrel.spawnMiniSquirrel(0);
        }
    }
}
