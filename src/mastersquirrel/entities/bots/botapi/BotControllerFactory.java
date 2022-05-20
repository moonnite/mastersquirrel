package mastersquirrel.entities.bots.botapi;

import mastersquirrel.XY;

public interface BotControllerFactory {
    default BotController createMasterBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                //API
            }
        };
    };
    default BotController createMiniBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext controllerContext) {
                XY dir = new XY(1,0);
                controllerContext.move(dir);
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