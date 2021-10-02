package com.tazza.javafxassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DynamicQuestionaire implements Initializable {
    public Button calculateScore;
    @FXML
    private  Button sendResult;
    @FXML
    private HBox hBoxQuestion;
    @FXML
    private VBox vBoxQuestion;
    @FXML
    public MainWindow mainWindow;
    public int score;
    @FXML
    private String nameUser;
    List<Questions> questionsList= new ArrayList<Questions>();
    List<Questions> resultQuestionnaire = new ArrayList<Questions>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       // questionsList = mainWindow.retrieveQuestions();
        System.out.println("size "+questionsList.size());
        sendResult.setOpacity(0);

    }
    public void retrieveQuestionaire(List<Questions> questionsList)throws IOException{
        this.questionsList=questionsList;
        for (int i = 0; i < questionsList.size(); i++) {
            System.out.println(questionsList.get(i).getQuestion());
        }
    }
    public void setParentController(MainWindow mainWindow)throws IOException {
        this.mainWindow=mainWindow;
        System.out.println(mainWindow);
        System.out.println(this.mainWindow);
    }
    public void displayQuestions() {
        for (int i = 0; i < questionsList.size(); i++) {
            Label lblQuest = new Label(questionsList.get(i).getQuestion());
            RadioButton rbn1= new RadioButton("Disagree");
            rbn1.setUserData(-1);
            RadioButton rbn2= new RadioButton("Neutral");
            rbn2.setUserData(0);
            RadioButton rbn3= new RadioButton("Agree");
            rbn3.setUserData(1);
            ToggleGroup tg = new ToggleGroup();
            rbn1.setToggleGroup(tg);
            rbn2.setToggleGroup(tg);
            rbn3.setToggleGroup(tg);
            HBox hb = new HBox();
            hb.setSpacing(50);
            hb.getChildren().add(lblQuest);
            hb.setSpacing(10);
            hb.getChildren().add(rbn1);
            hb.getChildren().add(rbn2);
            hb.getChildren().add(rbn3);
            vBoxQuestion.getChildren().add(hb);
        }
    }

    @FXML
    private void getRadioButtonSelected() {
        String answer="";
        /*String[] resultGroup1 = group1.getSelectedToggle().toString().split("'");
        String[] resultGroup2 = group2.getSelectedToggle().toString().split("'");
        String[] resultGroup3 = group3.getSelectedToggle().toString().split("'");
        String[] resultGroup4 = group4.getSelectedToggle().toString().split("'");
        String[] resultGroup5 = group5.getSelectedToggle().toString().split("'");
        String[] resultGroup6 = group6.getSelectedToggle().toString().split("'");
        String[] resultGroup7 = group7.getSelectedToggle().toString().split("'");
        String[] resultGroup8 = group8.getSelectedToggle().toString().split("'");
        String[] resultGroup9 = group9.getSelectedToggle().toString().split("'");*/

       /* resultQuestionnaire.add(resultGroup1[1]);
        resultQuestionnaire.add(resultGroup2[1]);
        resultQuestionnaire.add(resultGroup3[1]);
        resultQuestionnaire.add(resultGroup4[1]);
        resultQuestionnaire.add(resultGroup5[1]);
        resultQuestionnaire.add(resultGroup6[1]);
        resultQuestionnaire.add(resultGroup7[1]);
        resultQuestionnaire.add(resultGroup8[1]);
        resultQuestionnaire.add(resultGroup9[1]);*/
        for (int i = 0; i < vBoxQuestion.getChildren().size(); i++) {
            HBox hbox = (HBox) vBoxQuestion.getChildren().get(i);
           // System.out.println("HBOX CONTENT :"+hbox);
            for (int k = 0; k < hbox.getChildren().size(); k++) {
                Node node = hbox.getChildren().get(k);
                //RadioButton rbn = (RadioButton) node.getId();
                //System.out.println("RadioButton :"+rbn.getText()+" IS SELECTED ? :"+rbn.isSelected());
                if(node.getUserData()!=null) {
                    RadioButton rbn = (RadioButton) node;
                   // System.out.println("NODE CONTENT :"+rbn.isSelected()+" Int response :"+(Integer.parseInt(rbn.getUserData().toString())));
                    if(rbn.isSelected())
                        switch (Integer.parseInt(rbn.getUserData().toString())) {
                            case -1:
                                answer ="Disagree";
                                System.out.println("case -1 answer ="+answer+" Question = "+questionsList.get(i-1).getQuestion());
                                resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer,questionsList.get(i-1).getQuestionaireName()));
                                score-=1;
                            break;
                            case 0:
                                answer ="Neutral";
                                System.out.println("case 0 answer ="+answer+" Question = "+questionsList.get(i-1).getQuestion());
                                resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer, questionsList.get(i-1).getQuestionaireName()));
                                score+=0;
                            break;
                            case 1:
                                answer ="Agree";
                                System.out.println("case 1 answer ="+answer+" Question = "+questionsList.get(i-1).getQuestion());
                                resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer, questionsList.get(i-1).getQuestionaireName()));
                                score+=1;
                            break;
                            default:
                            break;

                        }
                        //System.out.println("VBOX = "+vBoxQuestion.getChildren().size()+" Res = "+resultQuestionnaire.size());


                    }
            }
        }

      /*  for(int i=0;i<resultQuestionnaire.size();i++) {
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
        resultCalculation.setText(String.valueOf(score));*/
        for (int i = 0; i < resultQuestionnaire.size(); i++) {
            System.out.println("Question no : "+resultQuestionnaire.get(i).getNumQuestion()
                    +" Question label : "+resultQuestionnaire.get(i).getQuestion()
                    +" Answer : "+resultQuestionnaire.get(i).getAnswer()
            +"Questionaire name : "+resultQuestionnaire.get(i).getQuestionaireName());
        }
        System.out.println("Name participant ="+nameUser+" Score ="+score);
    }

    public void sendResult(ActionEvent actionEvent) throws IOException {
        Participants p = new Participants(nameUser,score);
        mainWindow.items.clear();
        mainWindow.returnParticipant(p);
        mainWindow.getDynamicQuestionsList(resultQuestionnaire);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }
    public void reInit() {
        resultQuestionnaire.clear();
        score=0;
    }

    public void calculateScore(ActionEvent actionEvent) {
        reInit();
        getRadioButtonSelected();
        sendResult.setOpacity(1);
    }
    public void setNameUserText(String nameUserLabel) {
        this.nameUser=nameUserLabel;
    }
}
