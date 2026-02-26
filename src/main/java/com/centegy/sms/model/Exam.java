package com.centegy.sms.model;

public class Exam {

    public Exam(String examName, double marksObtained, double maxPossible){
        this.examName = examName;
        this.marksObtained = marksObtained;
        this.maxPossible = maxPossible;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public double getMaxPossible() {
        return maxPossible;
    }

    public void setMaxPossible(double maxPossible) {
        this.maxPossible = maxPossible;
    }

    private String examName;
    private double maxPossible;
    private double marksObtained;





}
