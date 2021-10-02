package com.tazza.javafxassignment;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Questionnaire {

    public GridPane gridPane;
    List<Questions> givenQuestionsList= new ArrayList<Questions>();
    public ToggleGroup group1;
    public ToggleGroup group2;
    public ToggleGroup group3;
    public ToggleGroup group4;
    public ToggleGroup group5;
    public ToggleGroup group6;
    public ToggleGroup group7;
    public ToggleGroup group8;
    public ToggleGroup group9;
    public int score;
    List<String> resultQuestionnaire = new ArrayList<String>();
    public MainWindow w;
    @FXML
    public Button calculateScorecalculateScore;
    @FXML
    public Button sendResultButton;
    @FXML
    public Label nameUserLabel;
    @FXML
    private Label resultCalculation;

    public Label getNameUserLabel() {
        return nameUserLabel;
    }
    private String participantName;

    public void setNameUserText(String nameUserLabel) {
        this.participantName=nameUserLabel;
        this.nameUserLabel.setText("Name of participant : "+nameUserLabel);
    }

    //public TextField nameUser;

    public void setCall(MainWindow w){
        this.w= w;
    }
    @FXML void initialize() {

        Platform.runLater(() -> {
      });

    }
    public void clickCalculate() {
        reInit();
        getRadioButtonSelected();
       // System.out.println(calculateScore.getText());
        sendResultButton.setOpacity(1);

    }

    @FXML
    private void getRadioButtonSelected() {
        String[] resultGroup1 = group1.getSelectedToggle().toString().split("'");
        String[] resultGroup2 = group2.getSelectedToggle().toString().split("'");
        String[] resultGroup3 = group3.getSelectedToggle().toString().split("'");
        String[] resultGroup4 = group4.getSelectedToggle().toString().split("'");
        String[] resultGroup5 = group5.getSelectedToggle().toString().split("'");
        String[] resultGroup6 = group6.getSelectedToggle().toString().split("'");
        String[] resultGroup7 = group7.getSelectedToggle().toString().split("'");
        String[] resultGroup8 = group8.getSelectedToggle().toString().split("'");
        String[] resultGroup9 = group9.getSelectedToggle().toString().split("'");

        resultQuestionnaire.add(resultGroup1[1]);
        resultQuestionnaire.add(resultGroup2[1]);
        resultQuestionnaire.add(resultGroup3[1]);
        resultQuestionnaire.add(resultGroup4[1]);
        resultQuestionnaire.add(resultGroup5[1]);
        resultQuestionnaire.add(resultGroup6[1]);
        resultQuestionnaire.add(resultGroup7[1]);
        resultQuestionnaire.add(resultGroup8[1]);
        resultQuestionnaire.add(resultGroup9[1]);
        List<Node> node = gridPane.getChildren();
        
        String[] test = node.toString().split("'");
        for (int i = 0; i < test.length; i++) {


            System.out.println("HBOX :"+test[i]);

        }
        for(int i=0;i<resultQuestionnaire.size();i++) {

            //HBox hb = (HBox) node;


            switch(resultQuestionnaire.get(i)) {
                case "Disagree":
                    score -=1;
                    break;
                case "Neutral":
                    score+=0;
                    break;
                case "Agree":
                    score+=1;
                    break;
                default:
                    break;
            }
        }
       // System.out.println("score="+score);
        resultCalculation.setText(String.valueOf(score));
    }

    public void sendResult(ActionEvent actionEvent) throws IOException {
        Participants p = new Participants(participantName,score);
        w.items.clear();
        w.returnParticipant(p);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }
    public void reInit() {
        resultQuestionnaire.clear();
        score=0;
    }
}
