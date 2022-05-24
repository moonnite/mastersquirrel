package mastersquirrel.entities.bots.botapi;

import mastersquirrel.RandomDirection;
import mastersquirrel.XY;

public interface BotControllerFactory {
    default BotController createMasterBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {

            }
        };
    };
    default BotController createMiniBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                XY dir = RandomDirection.getInstance().getRandom();
                controllerContext.move(dir);
//                if(RandomDirection.getInstance().randomInt(0,1) == 0){
//                    controllerContext.implode();
//                }
                controllerContext.implode();
            }
        };
    };
    default BotController createHandOperatedController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {

            }
        };
    };
}