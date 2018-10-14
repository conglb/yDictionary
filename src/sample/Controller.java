package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;
import readdict.Stardict;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField wordInput = new TextField();

    @FXML
    public Button searchButton = new Button();

    @FXML
    public Button soundButton = new Button();

    @FXML
    public TextArea wordDefi = new TextArea();

    @FXML
    public TextArea paraInput = new TextArea();

    @FXML
    public Button transButton = new Button();

    @FXML
    public TextArea transPara = new TextArea();

    @FXML
    public ListView<String> wordRela = new ListView<>();

    public Stardict readdict= new Stardict();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            readdict.loadDictionary("oxdict/en_vi.ifo", "oxdict/en_vi.idx",
                    "oxdict/en_vi.dict");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dictionary dict = new Dictionary(readdict);
        //Intend to multithread to load dict
        TextFields.bindAutoCompletion(wordInput, dict.wordslist);

    }

    public void searchEvent(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        String meaning = readdict.lookupWord(word);
        wordDefi.setText(meaning);
        // Button Sound call Word.phatammp3();
    }

    public void playSound(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        Word.phatam("en","vi",word);
        Word.phatammp3();
    }

    public void translateText(ActionEvent event) throws IOException{
        String text = paraInput.getText();
        transPara.setText(
                Word.dich("en","vi", text));
    }



}