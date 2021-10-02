package com.tazza.javafxassignment;

public class Questions {

    private String question;
    private int numQuestion;
    private String answer;
    private String questionaireName;

    public Questions(int numQuestion, String question,String answer,String questionaireName){
        this.question=question;
        this.numQuestion= numQuestion;
        this.answer=answer;
        this.questionaireName= questionaireName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public String getQuestionaireName() { return questionaireName; }

    public void setQuestionaireName(String questionaireName) { this.questionaireName = questionaireName; }

}
