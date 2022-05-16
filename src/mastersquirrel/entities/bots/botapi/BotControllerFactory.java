package mastersquirrel.entities.bots.botapi;

public interface BotControllerFactory {
    BotController createMasterBotController();
    BotController createMiniBotController();
}