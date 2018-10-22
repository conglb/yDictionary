package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private AnchorPane wordPane, paraPane, favoPane, modiPane;
    @FXML
    private JFXButton wordButton, paraButton, favoButton, modiButton;
    @FXML
    public TextField wordInput;
    @FXML
    public TextField modiTextField;
    @FXML
    public TextArea modiTextArea;
    @FXML
    public TextArea paraInput;
    @FXML
    public Label wordOutput, paraOutput, favoOutput;
    @FXML
    public Button translateButton;
    @FXML
    public Button favoAddButton;
    @FXML
    public Button addWordButton;
    @FXML
    public Button deleteWordButton;
    @FXML
    public Button modifyWordButton;

    public void handleTabButtonAction(javafx.event.ActionEvent event) {
        if (event.getSource() == wordButton) {
            wordPane.toFront();
        } else
        if (event.getSource() == paraButton) {
            paraPane.toFront();
        } else
        if (event.getSource() == favoButton) {
            favoPane.toFront();
        } else
        if (event.getSource() == modiButton) {
            modiPane.toFront();
        }
    }
/*
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
*/
    public Dictionary dict = new Dictionary();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(wordInput, t -> {
            return dict.wordslist.stream().filter(elem ->
            {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });
    }

    public void addWord(ActionEvent event) {
        dict.addWord(modiTextField.getText(), modiTextArea.getText());
    }

    public void deleteWord(ActionEvent event) {
        dict.deleteWord(modiTextField.getText());
    }

    public void modifyWord(ActionEvent event) {
        dict.modifyWord(modiTextField.getText(), modiTextArea.getText());
    }

    public void searchEvent(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        String meaning = dict.lookupWord(word);
        if (meaning != null) {
            wordOutput.setText(meaning);
            dict.addRecentlyWord(word);
        }
        else {
            wordOutput.setText("Not found!");
        }
    }

    public void playSound(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        Word.phatam("en","vi",word);
        Word.phatammp3();
    }

    public void translateText(ActionEvent event) throws IOException{
        String text = paraInput.getText();
        paraOutput.setText(
                Word.dich("en","vi", text));
    }



}