package com.tazza.javafxassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    @FXML
    public ListView<String> participantDisplay = new ListView<String>();;
    @FXML
    private Label labelName;
    List<Participants> participantList = new ArrayList<Participants>();
    ObservableList<String> items = FXCollections.observableArrayList ();
    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    @FXML
    private TextField name;
    public MainWindow() {
       // writeListParticipants();
    }
    @FXML
    protected void onMainWindowButtonClick() throws IOException {
        //welcomeText.setText("Welcome to JavaFX Application!");
        TextField nameUser = this.name;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Questionnaire.fxml"));
        Parent root = fxmlLoader.load();

        Questionnaire controller = fxmlLoader.getController();
        controller.setNameUserText(this.name.getText());
        controller.setCall(this);
        controller.sendResultButton.setOpacity(0);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Answer the questions...");

        stage.show();
    }
    public void returnParticipant(Participants p) throws IOException {
        boolean added=false;

        if(participantList.size()!=0) {
           for(int i=0;i<participantList.size();i++) {
                if(participantList.get(i).getName().equals(p.getName())) {
                    participantList.get(i).setScore(p.getScore() + participantList.get(i).getScore());

                    added=true;
                }
               createObservableList(participantList.get(i));
           }
            if(!added) {
                participantList.add(p);
                createObservableList(p);
            }
        } else {
            participantList.add(p);
            createObservableList(p);
        }
        writeListParticipants();
    }
    public void createObservableList(Participants p) {
        if(participantList.size()!=0) {
            String text = p.getName()+" "+p.getScore();
            items.add(text);
        }
    }
    public void writeListParticipants() {
        participantDisplay.setItems(items);

    }

}