package com.centegy.sms.model;

import com.centegy.sms.service.EnrolmentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    public List<Exam> getExamList() {
        return examList;
    }

    private final List<Exam> examList = new ArrayList<>();

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

    public int getAttendedSessions() {
        return attendedSessions;
    }

    public void setAttendedSessions(int attendedSessions) {
        this.attendedSessions = attendedSessions;
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    private final int enrolmentID;
    private LocalDate enrolmentStartDate;
    private LocalDate endDate;
    private Grade grade;
    private int numericScore;
    private int totalSessions = 0;
    private int attendedSessions = 0;
    private final Course course;
    private final Student student;



    public void updateFinalGrade() {
        if (examList.isEmpty()) {
            this.numericScore = 0;
            this.grade = Grade.N_A;
            return;
        }

        double total = 0;

        for (Exam e : examList) {
            total += e.getMarksObtained();
        }

        this.numericScore = (int) Math.round(total);

        this.grade = EnrolmentService.getGradeFromScore(this.numericScore);
    }

    public double getTotalMaxMarks() {
        double totalMax = 0;
        for (Exam e : examList) {
            totalMax += e.getMaxPossible();
        }
        return totalMax;
    }

    public double getAttendancePercentage() {
        if (totalSessions == 0) return 100.0; // Assume perfect until sessions start
        return ((double) attendedSessions / totalSessions) * 100;
    }

    public boolean isEligibleForFinals() {
        return getAttendancePercentage() >= 60.0;
    }

    public String getAttendanceStatus() {
        if (totalSessions == 0) {
            return "NO DATA (Classes haven't started)";
        }

        double percentage = getAttendancePercentage();

        if (percentage >= 70.0) {
            return "ELIGIBLE";
        } else if (percentage >= 60.0) {
            return "PROBATION";
        } else {
            return "INELIGIBLE";
        }
    }


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

