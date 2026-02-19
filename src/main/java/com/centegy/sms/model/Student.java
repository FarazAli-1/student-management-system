package com.centegy.sms.model;

public class Student {

    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private final int studentID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;


    public Student(int studentID, String firstName, String lastName, String phoneNumber, String email) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    @Override
    public String toString() {
        return "Student {" +
                "Student ID = " + studentID +
                ", First Name = '" + firstName + '\'' +
                ", Last Name = '" + lastName + '\'' +
                ", Phone Number = '" + phoneNumber + '\'' +
                ", Email = '" + email + '\'' +
                '}';
    }

}

