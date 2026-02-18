package com.centegy.sms.model;



public class Course {

    public Course(int courseID, String courseName, String courseCode, String credits, Instructor instructor, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.instructorID = instructor;
        this.maxCapacity = maxCapacity;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public Instructor getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Instructor instructorID) {
        this.instructorID = instructorID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    private int courseID;
    private String courseName;
    private String courseCode;
    private String credits;
    private Instructor instructorID;
    private int maxCapacity = 80;


    @Override
    public String toString() {

        String instructorName = (instructorID == null) ? "TBA" : instructorID.getFirstName();

        return "Course {" +
                "Course ID = " + courseID +
                ", Course Name = '" + courseName + '\'' +
                ", Course Code = '" + courseCode + '\'' +
                ", Credits = '" + credits + '\'' +
                ", Instructor = '" + instructorName + '\'' +
                ", Capacity = '" + maxCapacity + '\'' +
        '}';
    }

}
