package mastersquirrel.botimpls21;

import mastersquirrel.Log;
import mastersquirrel.RandomDirection;
import mastersquirrel.XY;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;

public class Namn implements BotControllerFactory {
    public Namn() {
        Log.log("Created Namn");
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
