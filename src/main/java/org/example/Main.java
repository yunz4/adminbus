package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = fxmlLoader.load();


        Image appIcon = new Image(getClass().getResourceAsStream("/bus.png"));
        primaryStage.getIcons().add(appIcon);
        primaryStage.setTitle("Admin BussApp");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }

}