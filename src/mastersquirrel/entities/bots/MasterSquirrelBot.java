package mastersquirrel.entities.bots;

import mastersquirrel.XY;
import mastersquirrel.entities.MasterSquirrel;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;

public class MasterSquirrelBot extends MasterSquirrel implements BotControllerFactory, BotController {

    public MasterSquirrelBot(XY pos) {
        super(pos);
    }

    @Override
    public void nextStep(ControllerContext view) {

    }

    @Override
    public BotController createMasterBotController() {
        return null;
    }

    @Override
    public BotController createMiniBotController() {
        return null;
    }
}
