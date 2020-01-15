/*
 * Course: SE2811
 * Winter 2019-2020
 * Lab 4 - The Flowers and the Bees
 * Name: Trenton Bowser and Matt Aleck
 * Created 12/12/2019
 */
package garden_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The driver class that starts the FXML stages
 */
public class GardenDriver extends Application {

    /**
     * Starts the main stage of the program
     *
     * @param primaryStage - main stage of the program
     * @throws Exception - ensures the UI will not break if the code does
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("garden_simulator.fxml"));
        primaryStage.setTitle("Bee Runner 2049");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }

    /**
     * Main function
     *
     * @param args - args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
