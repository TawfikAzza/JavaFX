package com.tazza.javafxassignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionaireTaken {

    Participants participant;
    List<Questions> questionsList= new ArrayList<>();
    List<Questions> dynamicQuestionsList= new ArrayList<>();
    String dynQuestionaireName;
    String questionaireName;
    public QuestionaireTaken (Participants p, List<Questions> questionsList, List<Questions> dynamicQuestionsList) throws IOException {
        this.participant=p;
        this.questionsList=questionsList;
        this.dynamicQuestionsList=dynamicQuestionsList;
        this.questionaireName=questionsList.get(0).getQuestionaireName();
        this.dynQuestionaireName=dynamicQuestionsList.get(0).getQuestionaireName();
    }
    public QuestionaireTaken(Participants p, List<Questions> questionsList) {
        this.participant=p;
        this.questionsList=questionsList;
        this.questionaireName=questionsList.get(0).getQuestionaireName();
    }
    public QuestionaireTaken(List<Questions> dynamicQuestionsList,Participants p) {
        this.participant=p;
        this.dynamicQuestionsList=dynamicQuestionsList;
        this.dynQuestionaireName=dynamicQuestionsList.get(0).getQuestionaireName();
    }

}
