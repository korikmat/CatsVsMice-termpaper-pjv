package game.fx.catsvsmice;

import com.google.gson.Gson;
import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.model.Sprite;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataSaver {
    GameModel gameModel;
    GameData gameData;
    private static final Logger LOGGER = Logger.getLogger(DataSaver.class.getName());

    public DataSaver(){
        gameData = new GameData();
        gameModel = new GameModel(gameData);
    }
    public DataSaver(GameModel gameModel, GameData gameData){
        this.gameModel = gameModel;
        this.gameData = gameData;
    }


    public void saveLvlToJson(){
        LOGGER.log(Level.INFO, "Starting saving LVL to json");

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


        int filesCount = countSavedFiles();

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("Level "+ filesCount)) {
            gson.toJson(gameData, writer);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Can''t save game data file: {0}", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Saving was successful");

    }

    public void loadLvlFromJson(String fileName){
        LOGGER.log(Level.INFO, "Starting loading {0} from json", fileName);

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            gameData = gson.fromJson(reader, GameData.class);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Can't load game from data file: {0}", e.getMessage());
            e.printStackTrace();
        }

        gameModel.clearPath();
        for(int i = 0; i < gameData.getPathCoords().size(); i+=2){
            double posX = gameData.getPathCoords().get(i);
            double posY = gameData.getPathCoords().get(i+1);
            System.out.println(posX +" "+ posY );
            gameModel.putPath(posX, posY);
        }

        gameModel.clearCats();
        for(int i = 0; i < gameData.getCatsCoords().size(); i+=2){
            double posX = gameData.getCatsCoords().get(i);
            double posY = gameData.getCatsCoords().get(i+1);
            System.out.println(posX +" "+ posY );
            gameModel.putCat(posX, posY);
        }
        LOGGER.log(Level.INFO, "Loading {0} from json was successful", fileName);
    }

    public int countSavedFiles() {
        File directory = new File(".");
        FilenameFilter filter = (dir, name) -> name.startsWith("Level") ;

        File[] files = directory.listFiles(filter);
        if (files != null) {
            return files.length;
        }

        return 0;
    }
}
