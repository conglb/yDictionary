package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Java FX here
/*
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
*/
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Dictionary dict = new Dictionary();
        String word = scanner.next();
        dict.show();
        dict.lookUp(word);
    }
}

