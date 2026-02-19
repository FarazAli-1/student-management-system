package com.centegy.sms.model;

public class Enrolment {

    public Enrolment(int enrolmentID, Student student, Course course) {
        this.enrolmentID = enrolmentID;
        this.student = student;
        this.course = course;
        this.grade = Grade.N_A;
        this.numericScore = 0;
        this.enrolmentStartDate = "N/A";
        this.endDate = "N/A";
    }

    public int getEnrolmentID() {
        return enrolmentID;
    }

    public String getEnrolmentStartDate() {
        return enrolmentStartDate;
    }

    public void setEnrolmentStartDate(String enrolmentStartDate) {
        this.enrolmentStartDate = enrolmentStartDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public int getNumericScore() {
        return numericScore;
    }

    public void setNumericScore(int numericScore) {
        this.numericScore = numericScore;
    }

    private int enrolmentID;
    private String enrolmentStartDate;
    private String endDate;
    private Grade grade;
    private int numericScore;
    private Course course;
    private Student student;

    @Override
    public String toString() {
        return "Enrolment {" +
                "ID = " + getStudent().getStudentID() +
                ", First Name = '" + getStudent().getFirstName() + " " + getStudent().getLastName() + '\'' +
                ", Enrolment Start Date = '" + enrolmentStartDate + '\'' +
                ", Grade = '" + grade + '\'' +
                ", Numeric Score = '" + numericScore + '\'' +
                ", Course Name = '" + getCourse().getCourseName() + '\'' +
                ", Course Code = '" + getCourse().getCourseCode() + '\'' +
                '}';
    }

}

