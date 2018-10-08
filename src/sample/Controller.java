package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField textField = new TextField();

    @FXML
    public TextArea textArea = new TextArea();

    @FXML
    public ListView<String> listView = new ListView<String>();

    ObservableList<String> list = FXCollections.observableArrayList(
            "java", "python", "c");
    ObservableList<String> tempList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(list);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textField.setText(newValue);
                    textArea.setText(newValue);
                });
//        textField.textProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    String currentInput = newValue;
//                    tempList.clear();
//                    list.forEach(item -> {
//                        if (currentInput.equals(item.substring(0,currentInput.length()-1))) tempList.add(item);
//                    listView.setItems(tempList);
//                });
//                });
    }

    public void textFieldSubition(ActionEvent event) {
        String userInput = textField.getText();
        if (listView.getItems().contains(userInput))
        listView.getSelectionModel().select(userInput);
        else textArea.setText("Not found.");
    }

    public void keyHandle(KeyEvent event) {
//        if (event.getCode() == KeyCode.KP_DOWN) listView.getSelectionModel().selectNext();
//        if (event.getCode() == KeyCode.KP_UP) listView.getSelectionModel().selectPrevious();
    }
}
