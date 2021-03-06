package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import org.controlsfx.control.textfield.TextFields;
import model.Dictionary;
import model.Word;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private AnchorPane wordPane, paraPane, modiPane;
    @FXML
    private JFXButton wordButton, paraButton, modiButton;
    @FXML
    public TextField wordInput;
    @FXML
    public TextField modiTextField;
    @FXML
    public TextArea modiTextArea;
    @FXML
    public TextArea paraInput;
    @FXML
    public Label wordOutput, paraOutput;
    @FXML
    public Button translateParaButton;
    @FXML
    public Button addWordButton;
    @FXML
    public Button deleteWordButton;
    @FXML
    public Button modifyWordButton;

    public Dictionary dict = new Dictionary();

    @FXML
    public ObservableList<String> items = FXCollections.observableArrayList(dict.recentlist);
    public ListView<String> recentlyList = new ListView<String>();

    public Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
    ButtonType buttonTypeCancel = new ButtonType( "No", ButtonBar.ButtonData.CANCEL_CLOSE );
    ButtonType buttonTypeOne = new ButtonType( "Yes" );


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // init search word textfield
        TextFields.bindAutoCompletion(wordInput, t -> {
            return dict.wordslist.stream().filter(elem ->
            {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });

        // init recently word in cache
        recentlyList.setItems(items);

        /**
         * init confirm box
         */
        alert.setTitle( "Confirm" );
        alert.setHeaderText( "Do you sure about this modify" );
        alert.setContentText( "Choose your option." );
        alert.initModality( Modality.NONE );
        alert.getButtonTypes().setAll( buttonTypeOne, buttonTypeCancel );

        Button b = new Button( "close alert" );
        b.setOnAction(( ActionEvent event ) ->
        {
            for ( ButtonType bt : alert.getDialogPane().getButtonTypes() )
            {
                System.out.println( "bt = " + bt );
                if ( bt.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE )
                {
                    Button cancelButton = ( Button ) alert.getDialogPane().lookupButton( bt );
                    cancelButton.fire();
                    break;
                }
            }
        });

        /**
         * commandline version run this code, comment it when use graphic version
         *
        try {
            dict.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    /**
     * AnchoPane manipulate
     */
    public void handleTabButtonAction(javafx.event.ActionEvent event) {
        if (event.getSource() == wordButton) {
            wordPane.toFront();
        } else
        if (event.getSource() == paraButton) {
            paraPane.toFront();
        } else
        if (event.getSource() == modiButton) {
            modiPane.toFront();
        }
    }

    /**
     * take the meaning of word and add to recently list
     * @param event
     * @throws IOException
     */
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
        recentlyList.getItems().add(0,word);
    }

    /**
     * pronooun word been search
     * @param event
     * @throws IOException
     */
    public void playSound(ActionEvent event) throws IOException {
        String word = wordInput.getText();
        Word.phatam("en","vi",word);
        Word.phatammp3();
    }

    /**
     * translate a paragraph
     * @param event
     * @throws IOException
     */
    public void translateText(ActionEvent event) throws IOException{
        paraOutput.setText("Translating...");
        String text = paraInput.getText();
        paraOutput.setText(
                Word.translate("en","vi", text));
    }

    /**
     * modify dictionary operator
     * @param event
     */
    public void addWord(ActionEvent event) {

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            dict.addWord(modiTextField.getText(), modiTextArea.getText());
        }
    }

    public void deleteWord(ActionEvent event) {
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == buttonTypeOne) {
            dict.deleteWord(modiTextField.getText());
        }
    }

    public void modifyWord(ActionEvent event) {
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == buttonTypeOne )
        {
            dict.modifyWord(modiTextField.getText(), modiTextArea.getText());
        }
    }



}