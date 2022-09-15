package se233.chapter4;

import javafx.application.Application;
import javafx.scene.Scene;
import se233.chapter4.controller.DrawingLoop;
import se233.chapter4.controller.GameLoop;
import se233.chapter4.view.Platform;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) { launch((args)); }
        @Override
        public void start(Stage primaryStage) {
            Platform platform = new Platform();
            GameLoop gameLoop = new GameLoop(platform);
            DrawingLoop drawingLoop = new DrawingLoop(platform);
            Scene scene = new Scene(platform, Platform.WIDTH, platform.HEIGHT);
            scene.setOnKeyPressed(event -> platform.getKeys().add(event.getCode()));
            scene.setOnKeyReleased(event -> platform.getKeys().remove(event.getCode()));
            primaryStage.setTitle("platformer");
            primaryStage.setScene(scene);
            primaryStage.show();
            (new Thread(gameLoop)).start();
            (new Thread(drawingLoop)).start();
        }
}
