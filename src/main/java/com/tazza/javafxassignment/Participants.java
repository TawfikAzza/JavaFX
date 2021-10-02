package com.tazza.javafxassignment;

import java.util.ArrayList;
import java.util.List;

public class Participants {



    private String name;
    private int score;
    List<Questions> questionsList= new ArrayList<Questions>();

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public List<Questions> getDynamicQuestionsList() {
        return dynamicQuestionsList;
    }

    public void setDynamicQuestionsList(List<Questions> dynamicQuestionsList) {
        this.dynamicQuestionsList = dynamicQuestionsList;
    }

    List<Questions> dynamicQuestionsList= new ArrayList<Questions>();
    public Participants(String name,int score){
        this.name=name;
        this.score=score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
