package com.centegy.sms.model;

import java.time.LocalDate;

public class Enrolment {

    public Enrolment(int enrolmentID, Student student, Course course) {
        this.enrolmentID = enrolmentID;
        this.student = student;
        this.course = course;
        this.grade = Grade.N_A;
        this.numericScore = 0;
        this.enrolmentStartDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(4);
    }

    public int getEnrolmentID() {
        return enrolmentID;
    }

    public LocalDate getEnrolmentStartDate() {
        return enrolmentStartDate;
    }

    public void setEnrolmentStartDate(LocalDate enrolmentStartDate) {
        this.enrolmentStartDate = enrolmentStartDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    private final int enrolmentID;
    private LocalDate enrolmentStartDate;
    private LocalDate endDate;
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

