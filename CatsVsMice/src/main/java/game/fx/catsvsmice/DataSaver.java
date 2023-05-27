package game.fx.catsvsmice;

import com.google.gson.Gson;
import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.model.Sprite;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The type Data saver.
 * Saves and loads levels from a json file.
 */
public class DataSaver {
    private static final Logger LOGGER = Logger.getLogger(DataSaver.class.getName());
    private final GameModel gameModel;
    private GameData gameData;

    /**
     * Instantiates a new Data saver.
     */
    public DataSaver(){
        gameData = new GameData();
        gameModel = new GameModel(gameData);
    }

    /**
     * Instantiates a new Data saver.
     * The GameData class and the GameModel class,
     * which are needed to load and unload the level,
     * are passed to the constructor.
     *
     * @param gameModel the game model
     * @param gameData  the game data
     */
    public DataSaver(GameModel gameModel, GameData gameData){
        this.gameModel = gameModel;
        this.gameData = gameData;
    }

    /**
     * Save lvl to json.
     * Copies the desired information from the model
     * to the date and saves the date class to json.
     */
    public void saveLvlToJson(){
        LOGGER.log(Level.INFO, "Starting saving LVL to json");

        setupDataToSave();

        int filesCount = countSavedFiles();
        LOGGER.log(Level.INFO, "Starting saving Level {0}", filesCount);

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("levels/Level "+ filesCount)) {
            gson.toJson(gameData, writer);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Can''t save game data file: {0}", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Successful!");

    }
    private void setupDataToSave(){
        double windowWidth = gameModel.getWindowWidth();
        double windowHeight = gameModel.getWindowHeight();

        gameData.setWindowWidth(windowWidth);
        gameData.setWindowHeight(windowHeight);

        List<Double> pathCoords = new ArrayList<>();
        for(Sprite sprite : gameModel.getPathSprites()){
            pathCoords.add(sprite.getPosX());
            pathCoords.add(sprite.getPosY());
        }
        gameData.setPathCoords(pathCoords);

        List<Double> catsCoords = new ArrayList<>();
        for(Sprite sprite : gameModel.getCatSprites()){
            catsCoords.add(sprite.getPosX());
            catsCoords.add(sprite.getPosY());
        }
        gameData.setCatsCoords(catsCoords);
    }

    /**
     * Load lvl from json.
     * Takes information from a json file by file name
     * and loads it into a date, then from the date the information
     * is passed to the model.
     *
     * @param fileName the file name of level
     */
    public void loadLvlFromJson(String fileName){
        LOGGER.log(Level.INFO, "Starting loading {0} from json", fileName);

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("levels/" + fileName)) {
            gameData = gson.fromJson(reader, GameData.class);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Can''t load game from data file: {0}", e.getMessage());
            e.printStackTrace();
        }

        setupModelToGame();

        LOGGER.log(Level.INFO, "Loading {0} from json was successful", fileName);
    }
    private void setupModelToGame(){
        double currWindowWidth = gameModel.getWindowWidth();
        double currWindowHeight = gameModel.getWindowHeight();

        double oldWindowWidth = gameData.getWindowWidth();
        double oldWindowHeight = gameData.getWindowHeight();

        gameModel.clearPath();
        for(int i = 0; i < gameData.getPathCoords().size(); i+=2){
            double posX = currWindowWidth*(gameData.getPathCoords().get(i)/oldWindowWidth);
            double posY = currWindowHeight*(gameData.getPathCoords().get(i+1)/oldWindowHeight);
            gameModel.putPath(posX, posY);
        }

        gameModel.clearCats();
        for(int i = 0; i < gameData.getCatsCoords().size(); i+=2){
            double posX = currWindowWidth*(gameData.getCatsCoords().get(i)/oldWindowWidth);
            double posY = currWindowHeight*(gameData.getCatsCoords().get(i+1)/oldWindowHeight);
            gameModel.putCat(posX, posY);
        }
        gameModel.resetMice();
        gameModel.resetGameStatistics();
    }

    /**
     * Count saved levels.
     *
     * @return the int
     */
    public int countSavedFiles() {
        File directory = new File("levels/");
        FilenameFilter filter = (dir, name) -> name.startsWith("Level") ;

        File[] files = directory.listFiles(filter);
        if (files != null) {
            return files.length;
        }

        return 0;
    }
}
