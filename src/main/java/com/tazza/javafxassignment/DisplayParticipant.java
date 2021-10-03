package com.tazza.javafxassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayParticipant implements Initializable {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buttonCLose;
    @FXML
    private VBox vBoxLeft;
    @FXML
    private VBox vBoxRight;
    @FXML
    private VBox vBoxCenter;
    @FXML
    private Label lblTitle;

    private int score;

    public void setName(String name) {
        this.name = pGiven.getName();
    }

    private String name;
    Participants pGiven;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // lblTitle.setText(questionsList.size()+"SIZE NAME :"+this.name);
    }
    public DisplayParticipant() {
       // setName(this.name);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public void setParticipant(Participants p) {
        this.pGiven=p;
    }

    public void displayParticipant() {
        lblTitle.setText("Participant name :"+pGiven.getName());
        lblTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        if(pGiven.questionsList.size()!=0) {
            for (int i = 0; i < pGiven.questionsList.size(); i++) {
                Label lblQuestion = new Label();
                //Font fontAnswer = new Font("Bold",12);
                Label lblAnswerGiven = new Label();
                lblAnswerGiven.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
                HBox hBoxLeft = new HBox();
                hBoxLeft.setSpacing(20);
                lblQuestion.setText(pGiven.questionsList.get(i).getQuestion());
                lblAnswerGiven.setText(pGiven.questionsList.get(i).getAnswer());
                hBoxLeft.getChildren().add(lblQuestion);
                hBoxLeft.getChildren().add(lblAnswerGiven);
                vBoxLeft.getChildren().add(hBoxLeft);

            }
        }
        if(pGiven.dynamicQuestionsList.size()!=0) {
            for (int i = 0; i < pGiven.dynamicQuestionsList.size(); i++) {
                Label lblQuestion = new Label();
                //Font fontAnswer = new Font("Bold",12);
                Label lblAnswerGiven = new Label();
                lblAnswerGiven.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
                HBox hBoxRight = new HBox();
                hBoxRight.setSpacing(20);
                lblQuestion.setText(pGiven.dynamicQuestionsList.get(i).getQuestion());
                lblAnswerGiven.setText(pGiven.dynamicQuestionsList.get(i).getAnswer());
                hBoxRight.getChildren().add(lblQuestion);
                hBoxRight.getChildren().add(lblAnswerGiven);
                vBoxRight.getChildren().add(hBoxRight);

            }
        }

    }




}
