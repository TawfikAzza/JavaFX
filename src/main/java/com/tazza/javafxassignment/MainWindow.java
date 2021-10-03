package com.tazza.javafxassignment;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Application {
    //button to add a new questionaire
    public Button addQuestionnaire;
    //button that start the new questionaire form routine
    public Button dynQuestButton;
    public Label lblMessage;
    //List of questions from the new questionaire stored in a arrayList, (is it possible to have a class store an arrayType of datas?
    List<Questions> questionsList = new ArrayList<>();
    //List of questions from the teachers as well as the answers given by the user
    List<Questions> dynamicQuestionsList = new ArrayList<>();
    //This is the list of participants in the Liste on the right of the scene.
    List<QuestionaireTaken> questionaireTakens = new ArrayList<>();
    @FXML
    private ListView<String> participantDisplay = new ListView<>();

    @FXML
    private Label labelName;
    List<Participants> participantList = new ArrayList<>();
    //Array to register the Participant in a String Array List type, the ListView seems to
    // only accpets ObservableList type of data (There surely exists a better way to do that)
    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private TextField name;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }


    public MainWindow() {

    }

    @FXML
    protected void onMainWindowButtonClick() throws IOException {
        if (checkNameUser()) {
            TextField nameUser = this.name;
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("Questionnaire.fxml"));
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
    }

    public void addQuestionnaire(ActionEvent actionEvent) throws IOException {
        lblMessage.setText("");
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("NewQuestionsPane.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        NewQuestionsPane newQuestionsPaneController = fxmlLoader.getController();
        newQuestionsPaneController.setParentController(this);
        stage.setScene(scene);
        stage.setTitle("Welcome");
        stage.show();
    }

    public void getNewQuestions(List<Questions> questionsList) {
        this.dynamicQuestionsList = questionsList;
    }

    public List<Questions> retrieveQuestions() {
        if (dynamicQuestionsList.size() != 0) {
            return dynamicQuestionsList;
        } else {
            return null;
        }
    }

    public void returnParticipant(Participants p, String TypeQuestionaire) throws IOException {
        boolean added = false;
        boolean existInList = false;
        System.out.println(TypeQuestionaire);

        if (participantList.size() == 0) {
            participantList.add(p);
            added=true;
        }

        for (Participants participants : participantList) {
            if (participants.getName().equals(p.getName())) {
                existInList = true;

            } else if (!existInList) {
                existInList = false;
            }
        }

        if (participantList.size() != 0 && !existInList) {
            participantList.add(p);
            added=true;
        }

        if (participantList.size() != 0) {
            for (int i = 0; i < participantList.size(); i++) {
                System.out.println("SIZE :" + participantList.size() + "IN FOR " + participantList.get(i).getName().equals(p.getName()));
                if (participantList.get(i).getName().equals(p.getName())) {
                    System.out.println("IN FIRST IF :");

                    if (TypeQuestionaire.equals("basic")) {
                        System.out.println("BASIC IF ELSE IN RETURN PARTICPANTS : " + questionsList.size());
                        if(!added) {
                            participantList.get(i).setScore(p.getScore() + participantList.get(i).getScore());
                        }
                        participantList.get(i).setQuestionsList(questionsList);

                    } else {
                        System.out.println("DYNAMIC IF ELSE IN RETURN PARTICPANTS");
                        if(!added) {
                            participantList.get(i).setScore(p.getScore() + participantList.get(i).getScore());
                        }
                        participantList.get(i).setDynamicQuestionsList(dynamicQuestionsList);
                    }
                    added = true;
                }
                createObservableList(participantList.get(i));
            }
            if (!added) {
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
        if (participantList.size() != 0) {
            String text = p.getName() + " " + p.getScore();
            items.add(text);
        }
    }

    public void writeListParticipants() {
        participantDisplay.setItems(items);

    }

    public MainWindow getReference() {
        return this;
    }

    public void dynamicQuest(ActionEvent actionEvent) throws IOException {
        if (checkNameUser())
            if (retrieveQuestions() != null) {
                URL fxmlLocation = getClass().getResource("DynamicQuestionaire.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
               Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                DynamicQuestionaire dynamicQuestionaire = loader.getController();
                dynamicQuestionaire.setParentController(this);
                dynamicQuestionaire.retrieveQuestionaire(dynamicQuestionsList);
                dynamicQuestionaire.displayQuestions();
                dynamicQuestionaire.setNameUserText(name.getText());
                stage.setScene(scene);
                stage.setTitle("Welcome");
                stage.show();
            } else {
                lblMessage.setText("No new questionaires has been currently added!");
            }

    }

    public boolean checkNameUser() {
        if (name.getText() == "") {
            lblMessage.setText("Please enter an user name");
            return false;
        } else {
            return true;
        }
    }

    public void getDynamicQuestionsList(List<Questions> dynamicQuestionsList) {
        this.dynamicQuestionsList = dynamicQuestionsList;
    }

    public void getQuestionsList(List<Questions> givenQuestionsList) {
        this.questionsList = givenQuestionsList;
        for (int i = 0; i < givenQuestionsList.size(); i++) {
            System.out.println(" Question = " + givenQuestionsList.get(i).getQuestion() + " Answer " + givenQuestionsList.get(i).getAnswer());
        }
    }

    public void clickMouseListView(MouseEvent mouseEvent) throws IOException {
        String[] listViewCandidat;
        String participant = "";
        int scoreParticipant = 0;
        System.out.println(mouseEvent.getClickCount());
        ObservableList<String> topics;
        topics = participantDisplay.getSelectionModel().getSelectedItems();
        Participants p = new Participants("", 0);
        //Use a for each in order to get the value contained in the Listview
        for (String each : topics) {
            listViewCandidat = each.split(" ");
            scoreParticipant = Integer.parseInt(listViewCandidat[1]);
            participant = listViewCandidat[0];
            System.out.println("participant :" + participant + " score " + scoreParticipant);
        }
        //I add the participants list clicked which correspond to the name of the participant selected
        //need to loop in a for loop is needed, maybe there is a better way
        for (int i = 0; i < participantList.size(); i++) {
            if (participantList.get(i).getName().equals(participant)) {
                p = participantList.get(i);
            }
        }
        //if the participant name is not empty, it means the click on the listview returned a valid participant in the list and
        //known in the participant list stored in the program
        //
        if (p.getName() != "") {
            System.out.println("Participant p name =" + p.getName() + "SIZE = " + p.questionsList.size());
            for (int i = 0; i < p.questionsList.size(); i++) {
                System.out.println(" Question :" + p.questionsList.get(i).getQuestion() + " Answer : " + p.questionsList.get(i).getAnswer());
            }
            for (int i = 0; i < p.dynamicQuestionsList.size(); i++) {
                System.out.println(" Question :" + p.dynamicQuestionsList.get(i).getQuestion() + " Answer : " + p.dynamicQuestionsList.get(i).getAnswer());
            }
        }
        //AT ths point in the program, I have everything I need in order to display the user Questions as well as the answer
        // he/she provided for it be it the dynamic generated one or the static one(given by the teacher
        //so now we have to generate the fxml loader with the display page and send the information to this
        //fxml Controller (Name DiplayParticipant)
        URL fxmlLocation = getClass().getResource("DisplayParticipant.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        DisplayParticipant displayParticipant = loader.getController();
        displayParticipant.setParticipant(p);
        displayParticipant.displayParticipant();

        stage.setScene(scene);
        stage.setTitle("Overview of " + p.getName());
        stage.show();


    }


    public void viewStat(ActionEvent actionEvent) throws IOException {
        questionaireTakens.clear();
        if (participantList.size() != 0) {
            for (int i = 0; i < participantList.size(); i++) {
                if (participantList.get(i).questionsList.size() != 0 && participantList.get(i).dynamicQuestionsList.size() != 0) {
                    questionaireTakens.add(new QuestionaireTaken(participantList.get(i), participantList.get(i).questionsList, participantList.get(i).dynamicQuestionsList));
                }
                if (participantList.get(i).questionsList.size() != 0 && participantList.get(i).dynamicQuestionsList.size() == 0) {
                    questionaireTakens.add(new QuestionaireTaken(participantList.get(i), participantList.get(i).questionsList));
                }
                if (participantList.get(i).questionsList.size() == 0 && participantList.get(i).dynamicQuestionsList.size() != 0) {
                    questionaireTakens.add(new QuestionaireTaken(participantList.get(i).dynamicQuestionsList, participantList.get(i)));
                }
            }
            for (QuestionaireTaken questionaireTaken : questionaireTakens) {
                if (questionaireTaken.questionaireName != null) {
                    System.out.println("Participant : " + questionaireTaken.participant.getName() + " Questionaire : " +
                    questionaireTaken.questionaireName+" Score attained "+questionaireTaken.participant.getScore());
                }
                if(questionaireTaken.dynQuestionaireName!=null) {
                    System.out.println("Participant : " + questionaireTaken.participant.getName() + " Questionaire : " +
                            questionaireTaken.dynQuestionaireName+" Score attained "+questionaireTaken.participant.getScore());
                }

            }


        }
    }
}