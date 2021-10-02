package com.tazza.javafxassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewQuestionsPane {

    public TextField questionaireName;
    @FXML
    private Button confirmButton;
    @FXML
    private VBox leftVbox;
    @FXML
    private Button deleteButton;
    @FXML
    private Label lblMessage;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox centerVbox;
    private int indexQuestion=1;

    String numQuestion="";
    String nameQuestion="";

    List<Questions> questionsList= new ArrayList<Questions>();

    private MainWindow mainWindow;

    public void addQuestionLine(ActionEvent actionEvent) {

        lblMessage.setText("");
        TextField nameQuestion = new TextField();
        centerVbox.getChildren().add(nameQuestion);

        indexQuestion++;

    }

    public void setParentController(MainWindow mainWindow) throws IOException {
        this.mainWindow=mainWindow;
    }

    public void deleteQuestionLine(ActionEvent actionEvent) {
        if(centerVbox.getChildren().size()>0) {
            centerVbox.getChildren().remove(centerVbox.getChildren().size()-1);
        } else {
            lblMessage.setText("There is no more questions to delete !");
        }
    }


    public void confirmQuestion(ActionEvent actionEvent) {
        addQuestionLine(actionEvent);
        for (int i = 0; i < centerVbox.getChildren().size()-1; i++) {
            TextField txtF = (TextField)centerVbox.getChildren().get(i);
            questionsList.add(new Questions(i+1,txtF.getText(),"",questionaireName.getText()));
            //System.out.println("No. question :"+i+" "+txtF.getText()+" Added");
        }
        mainWindow.getNewQuestions(questionsList);

        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
