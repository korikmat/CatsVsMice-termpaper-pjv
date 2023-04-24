package korikmat.cz.cvut.fel.pjv.termpaper.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Editor extends Application {

  @Override
  public void start(Stage primaryStage) {
    var javaVersion = SystemInfo.javaVersion();
    var javafxVersion = SystemInfo.javafxVersion();

    var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    var scene = new Scene(new StackPane(label), 640, 480);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() {

  }

  public static void main(String[] args) {
    launch(args);
  }

  private void addPathPoint(double toX, double toY) {

  }

  private void addCheese(double x, double y) {

  }
}