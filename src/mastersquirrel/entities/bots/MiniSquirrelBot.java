package mastersquirrel.entities.bots;

import mastersquirrel.EntityContext;
import mastersquirrel.XY;
import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.MiniSquirrel;
import mastersquirrel.entities.bots.botapi.BotController;
import mastersquirrel.entities.bots.botapi.BotControllerFactory;
import mastersquirrel.entities.bots.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel implements BotControllerFactory, BotController {

    public MiniSquirrelBot(int energy, XY pos, AEntity parent) {
        super(energy, pos, parent);
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
