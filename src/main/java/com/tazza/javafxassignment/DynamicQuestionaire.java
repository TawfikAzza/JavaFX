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
        //System.out.println("size "+questionsList.size());
        sendResult.setOpacity(0);

    }
    public void retrieveQuestionaire(List<Questions> questionsList)throws IOException{
        this.questionsList=questionsList;
       /* for (int i = 0; i < questionsList.size(); i++) {
            System.out.println(questionsList.get(i).getQuestion());
        }*/
    }
    public void setParentController(MainWindow mainWindow)throws IOException {
        this.mainWindow=mainWindow;
        //System.out.println(mainWindow);
        //System.out.println(this.mainWindow);
    }
    public void displayQuestions() {
        for (int i = 0; i < questionsList.size(); i++) {
            Label lblQuest = new Label(questionsList.get(i).getQuestion());
            RadioButton rbn1= new RadioButton("Disagree");
            rbn1.setUserData(-1);
            RadioButton rbn2= new RadioButton("Neutral");
            rbn2.setSelected(true);
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

        for (int i = 0; i < vBoxQuestion.getChildren().size(); i++) {
            HBox hbox = (HBox) vBoxQuestion.getChildren().get(i);
            for (int k = 0; k < hbox.getChildren().size(); k++) {
                Node node = hbox.getChildren().get(k);

                if(node.getUserData()!=null) {
                    RadioButton rbn = (RadioButton) node;
                   if(rbn.isSelected())
                        switch (Integer.parseInt(rbn.getUserData().toString())) {
                            case -1:
                                answer ="Disagree";
                                resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer,questionsList.get(i-1).getQuestionaireName()));
                                score-=1;
                            break;
                            case 0:
                                answer ="Neutral";
                                 resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer, questionsList.get(i-1).getQuestionaireName()));
                                score+=0;
                            break;
                            case 1:
                                answer ="Agree";
                                 resultQuestionnaire.add(new Questions(i,questionsList.get(i-1).getQuestion(),answer, questionsList.get(i-1).getQuestionaireName()));
                                score+=1;
                            break;
                            default:
                            break;

                        }



                    }
            }
        }
    }

    public void sendResult(ActionEvent actionEvent) throws IOException {
        Participants p = new Participants(nameUser,score);
        mainWindow.items.clear();
        mainWindow.getDynamicQuestionsList(resultQuestionnaire);
        mainWindow.returnParticipant(p,"Dynamic");

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
