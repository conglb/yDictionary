package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import readdict.Stardict;



// Java FX here
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
