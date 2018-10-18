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
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnHidden(e -> {
            controller.beforeExit();
            Platform.exit();
        });
    }



    public static void main(String[] args) throws IOException {
        // phan nay toi cmt lai vi k biet no lam gi
        //Stardict readdict = new Stardict();
        //readdict.loadDictionary("oxdict/en_vi.ifo", "oxdict/en_vi.idx",
        //        "oxdict/en_vi.dict");
        //Dictionary dict = new Dictionary(readdict);
        launch(args);
    }
}

/*
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
*/


