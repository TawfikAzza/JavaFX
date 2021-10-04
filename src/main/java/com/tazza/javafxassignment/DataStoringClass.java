package com.tazza.javafxassignment;

public class DataStoringClass {

    private String question;
    private String answer;
    private int scoreAnswer;



    public DataStoringClass(String question, String answer){
        this.question=question;
        this.answer=answer;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScoreAnswer() {
        return scoreAnswer;
    }

    public void setScoreAnswer(int scoreAnswer) {
        this.scoreAnswer = scoreAnswer;
    }
}
