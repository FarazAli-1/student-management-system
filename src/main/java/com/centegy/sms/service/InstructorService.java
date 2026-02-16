package com.centegy.sms.service;

import com.centegy.sms.model.Instructor;
import com.centegy.sms.model.Student;

import java.util.ArrayList;
import java.util.List;

public class InstructorService {

    private List<Instructor> instructors = new ArrayList<>();
    private static int nextID = 1;

    public void addInstructor(String firstName, String lastName, String phoneNumber, String email){
        for (Instructor ins : instructors) {
            if (ins.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Error: A Instructor with email " + email + " already exists.");
                return;
            }
        }
        Instructor instructor = new Instructor(
                nextID++,
                firstName,
                lastName,
                phoneNumber,
                email
        );

        instructors.add(instructor);
    }

    public Instructor findInstructor(int instructorID){

        for (Instructor i : instructors){
            if (i.getInstructorID() == instructorID){
                return i;
            }
        }

        return null;
    }

    public boolean removeInstructor (int instructorID){
        return instructors.removeIf(i -> i.getInstructorID() == instructorID);
    }

    public void showInstructors (){

        if (instructors.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("%-10s | %-30s | %-20s | %-30s\n", "ID" , "Name", "Phone Number", "Email");
            System.out.println("----------------------------------------------------------------------------");
            for (Instructor instructor : instructors) {
                System.out.printf("%-10s | %-30s | %-20s | %-30s\n",
                        instructor.getInstructorID(),
                        instructor.getFirstName() + " " + instructor.getLastName(),
                        instructor.getPhoneNumber(),
                        instructor.getEmail());
            }
            System.out.println();
        }
    }


}
