package mastersquirrel.botimpls21;

public class Asam {

    public Asam() {
        System.out.println("Created Asam");
    }

    @Override
    public BotController createMasterBotController() {
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                XY dir = RandomDirection.getInstance().getRandom();
                controllerContext.move(dir);
                controllerContext.spawnMiniBot(XY.ZERO_ZERO.genNewDir(), 100);
            }
        };
    }

    @Override
    public BotController createMiniBotController() {
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                XY dir = RandomDirection.getInstance().getRandom();
                controllerContext.move(dir);
                if(RandomDirection.getInstance().randomInt(0,100) == 0){
                    controllerContext.implode(10);
                }
            }
        };
    }
}