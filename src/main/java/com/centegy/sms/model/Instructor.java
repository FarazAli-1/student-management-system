package com.centegy.sms.model;

public class Instructor {

    public int getInstructorID() {
        return instructorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private final int instructorID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Instructor(int instructorID, String firstName, String lastName, String phoneNumber, String email) {
        this.instructorID = instructorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Instructor {" +
                "Instructor ID = " + instructorID +
                ", First Name = '" + firstName + '\'' +
                ", Last Name = '" + lastName + '\'' +
                ", Phone Number = '" + phoneNumber + '\'' +
                ", Email = '" + email + '\'' +
                '}';
    }
}
