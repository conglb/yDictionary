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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    public Dictionary dict = new Dictionary();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(wordInput, t -> {
            return dict.wordslist.stream().filter(elem ->
            {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });

        dict.addWord("oki", "dong y");
    }


    /*
    public void addWord(ActionEvent event) {
        dict.addWord(addWordWord.getText(), addWordMean.getText);
    }

    public void deleteWord(ActionEvent event) {
        dict.deleteWord(deleteWordWord.getText());
    }

    public void modifyWord(ActionEvent event) {
        dict.modifyWord(modifyWordWord.getText(), modifyWordMean.getText());
    }
    */

    public void searchEvent(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        String meaning = dict.lookupWord(word);
        if (meaning != null) {
            wordDefi.setText(meaning);
            dict.addRecentlyWord(word);
        }
        else {
            wordDefi.setText("Not found!");
        }
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