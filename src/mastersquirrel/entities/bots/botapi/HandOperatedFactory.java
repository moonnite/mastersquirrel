package mastersquirrel.entities.bots.botapi;

import mastersquirrel.RandomDirection;
import mastersquirrel.XY;

public class HandOperatedFactory implements BotControllerFactory{

    @Override
    public BotController createMasterBotController() {
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {

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
