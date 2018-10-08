package sample;

import javafx.application.Application;
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

    public static void main(String[] args) throws ClassNotFoundException, IOException, FileNotFoundException, LineUnavailableException, UnsupportedAudioFileException {
        Scanner scanner = new Scanner(System.in);
        Dictionary dict = new Dictionary();
        String word = scanner.nextLine();
        System.out.println(word);
        dict.show();
        dict.lookUp(word);
        System.out.println(Word.dich("en","vi",word));
        Word.phatam("en","vi",word);
        Word.phatammp3();


        // Read dict
        Stardict readdict= new Stardict();
        readdict.loadDictionary("oxdict/en_vi.ifo", "oxdict/en_vi.idx",
                "oxdict/en_vi.dict");
        String meaning = readdict.lookupWord(word);
        System.out.println(meaning);

    }
}


