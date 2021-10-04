package com.tazza.javafxassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class DisplayQuestionStat {

    @FXML
    private BarChart<String,Number> barChart;
    //private BarChart<String,String> barChart;
    private List<QuestionaireTaken> qt = new ArrayList<>();
    private List<DataStoringClass> dst = new ArrayList<>();
    private List<DataStoringClass> dstSorted = new ArrayList<>();
    private List<DataStoringClass> dstFinal = new ArrayList<>();

    public void displayChart(ActionEvent actionEvent) {
        boolean questionList= false;
        boolean dynaList = false;
        boolean added = false;
        boolean existInList = false;
        Axis<String> xAxis = barChart.getXAxis();
        Axis<Number> yAxis = barChart.getYAxis();

        barChart.setTitle("Questions Summary");
        xAxis.setLabel("Questions Taken");
        yAxis.setLabel("Answer Given");
        xAxis.setAnimated(false);
        /*XYChart.Series<String,String> seriesDisagree = new XYChart.Series<>();
        XYChart.Series<String,String> seriesNeutral = new XYChart.Series<>();
        XYChart.Series<String,String> seriesAgree = new XYChart.Series<>();*/
        XYChart.Series<String,Number> seriesDisagree = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesNeutral = new XYChart.Series<>();
        XYChart.Series<String,Number> seriesAgree = new XYChart.Series<>();

        seriesDisagree.setName("Disagree");
        seriesNeutral.setName("Neutral");
        seriesAgree.setName("Agree");

        dstSorted.clear();
        dst.clear();
        dstFinal.clear();
        //Creer une classe de donnees afin de stocker les question associees avec le score des reponses que les
        //participants ont donner.(ca va etre la merde a coder....)
        //qt.dynamicQuestionsList.get()
        for (int i = 0; i < qt.size(); i++) {
            if(qt.get(i).questionsList.size()!=0) {
                questionList=true;
            }
            if(qt.get(i).dynamicQuestionsList.size()!=0) {
                dynaList=true;
            }
        }
        if (questionList) {
            for (QuestionaireTaken qta : qt) {
                for (Questions q : qta.questionsList) {
                    //System.out.println(" Questions : " + q.getQuestion() + " Answer :" + q.getAnswer());
                    dst.add(new DataStoringClass(q.getQuestion(), q.getAnswer()));
                }
                //System.out.println("Next Questionnaire : " + qt.size());
            }
        }
        if (dynaList) {
            for (QuestionaireTaken qta : qt) {
                for (Questions q : qta.dynamicQuestionsList) {
                    //System.out.println(" Questions : " + q.getQuestion() + " Answer :" + q.getAnswer());
                    dst.add(new DataStoringClass(q.getQuestion(), q.getAnswer()));
                }
               // System.out.println("Next Questionnaire : " + qt.size());
            }
        }
        questionList= false;
        dynaList = false;
        added = false;
        existInList = false;
        //System.out.println("Out of foreach ");
        for (int i = 0; i < dst.size(); i++) {
            //System.out.println(" Question : "+dst.get(i).getQuestion()+" Answer "+dst.get(i).getAnswer()+" SCORE "+dst.get(i).getScoreAnswer());
            if(dstSorted.size()==0) {
                dstSorted.add(new DataStoringClass(dst.get(i).getQuestion(),dst.get(i).getAnswer()));
                added=true;
            }
            for (DataStoringClass dstCheck : dstSorted) {
                if(dst.get(i).getQuestion().equals(dstCheck.getQuestion())) {
                    existInList = true;
                  //  System.out.println("IN OF OF EQUAL ");
                } else {
                    //System.out.println(" Question : "+dst.get(i).getQuestion()+" Answer "+dst.get(i).getAnswer()+"NOT ADDED");
                } /*else if(!existInList){
                    existInList = false;
                }*/
            }
            if(!existInList){
                dstSorted.add(dst.get(i));
            }
            existInList = false;
        }

       // System.out.println("After Treatment:");
       // System.out.println("-----------------");
       /* for (int i = 0; i < dstSorted.size(); i++) {
            System.out.println(" Question : "+dstSorted.get(i).getQuestion()+" Answer "+dstSorted.get(i).getAnswer());
        }*/
        questionList= false;
        dynaList = false;
        added = false;
        existInList = false;

        for (int i = 0; i < dst.size(); i++) {
            //System.out.println(" Question : "+dst.get(i).getQuestion()+" Answer "+dst.get(i).getAnswer());

            for (int k = 0; k < dstSorted.size(); k++) {
                for (int j = 0; j < dstSorted.size(); j++) {
                    if(dst.get(i).getQuestion().equals(dstSorted.get(j).getQuestion())) {
                        if(dst.get(i).getAnswer().equals(dstSorted.get(j).getAnswer())){
                            existInList=true;
                        }
                    }
                }
                if(dst.get(i).getQuestion().equals(dstSorted.get(k).getQuestion())) {
                    if(dst.get(i).getAnswer().equals(dstSorted.get(k).getAnswer())) {
                        dstSorted.get(k).setScoreAnswer(dstSorted.get(k).getScoreAnswer()+1);

                    } else if(!existInList){
                        //dst.get(i).setScoreAnswer(dst.get(i).getScoreAnswer()+1);
                        /*System.out.println("QUESTION IN DST "+dst.get(i).getQuestion()
                                +" QUESTION IN DSTSORTED "+dstSorted.get(k).getQuestion()
                                +" ANSWER IN DST "+dst.get(i).getAnswer()
                                +" ANSWER IN DSTSORTED "+dstSorted.get(k).getAnswer());
                        System.out.println("Question "+dst.get(i).getQuestion()+" answer "+dst.get(i).getAnswer()+" ADDED! ");*/
                        dstSorted.add(dst.get(i));
                    }
                } else {
                   // System.out.println(" Question : "+dst.get(i).getQuestion()+" Answer "+dst.get(i).getAnswer()+"NOT ADDED");
                }
            }
            existInList=false;
        }
        //System.out.println("After Treatment dstFinal:");
        //System.out.println("-----------------");
        for (int i = 0; i < dstSorted.size(); i++) {
            if(dstSorted.get(i).getAnswer().equals("Disagree")) {
               seriesDisagree.getData().add(new XYChart.Data(dstSorted.get(i).getQuestion(),dstSorted.get(i).getScoreAnswer()));
                //System.out.println(" QUESTION ="+dstSorted.get(i).getQuestion()+" ANSWER : "+dstSorted.get(i).getAnswer());
            }
            if(dstSorted.get(i).getAnswer().equals("Neutral")) {
                seriesNeutral.getData().add(new XYChart.Data(dstSorted.get(i).getQuestion(),dstSorted.get(i).getScoreAnswer()));
                //System.out.println(" QUESTION ="+dstSorted.get(i).getQuestion()+" ANSWER : "+dstSorted.get(i).getAnswer());
            }
            if(dstSorted.get(i).getAnswer().equals("Agree")) {
                seriesAgree.getData().add(new XYChart.Data(dstSorted.get(i).getQuestion(),dstSorted.get(i).getScoreAnswer()));
                //System.out.println(" QUESTION ="+dstSorted.get(i).getQuestion()+" ANSWER : "+dstSorted.get(i).getAnswer());
            }

           // System.out.println(" Question : "+dstSorted.get(i).getQuestion()+" Answer "+dstSorted.get(i).getAnswer()+" Score "+dstSorted.get(i).getScoreAnswer());
        }

        barChart.getData().add(seriesDisagree);
        barChart.getData().add(seriesNeutral);
        barChart.getData().add(seriesAgree);


    }

    public void setQt(List<QuestionaireTaken> qt) {
        this.qt = qt;
    }

}
