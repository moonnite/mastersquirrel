package mastersquirrel.entities.bots.botapi;

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
        return null;
    }
}
