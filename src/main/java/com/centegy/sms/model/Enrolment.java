package com.centegy.sms.model;

import java.time.LocalDate;

public class Enrolment {

    public Enrolment(int enrolmentID, LocalDate enrolmentStartDate, LocalDate endDate, Grade grade, Course course, Student student) {
        this.enrolmentID = enrolmentID;
        this.enrolmentStartDate = enrolmentStartDate;
        this.endDate = endDate;
        this.grade = grade;
        this.course = course;
        this.student = student;
    }

    public Enrolment(int enrolmentID, Student student, Course course) {
        this.enrolmentID = enrolmentID;
        this.student = student;
        this.course = course;
    }



    public int getEnrolmentID() {
        return enrolmentID;
    }

    public void setEnrolmentID(int enrolmentID) {
        this.enrolmentID = enrolmentID;
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

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private int enrolmentID;
    private LocalDate enrolmentStartDate;
    private LocalDate endDate;
    private Grade grade;
    private Course course;
    private Student student;

//    @Override
//    public String toString() {
//        return "Instructor {" +
//                "Instructor ID = " + instructorID +
//                ", First Name = '" + firstName + '\'' +
//                ", Last Name = '" + lastName + '\'' +
//                ", Phone Number = '" + phoneNumber + '\'' +
//                ", Email = '" + email + '\'' +
//                '}';
//    }

}

