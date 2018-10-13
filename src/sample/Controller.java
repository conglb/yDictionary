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

    }

    public void searchEvent(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        String meaning = readdict.lookupWord(word);
        wordDefi.setText(meaning);
    }



}
