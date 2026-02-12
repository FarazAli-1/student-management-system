package com.centegy.sms.service;

import com.centegy.sms.model.Instructor;
import com.centegy.sms.model.Student;

import java.util.ArrayList;
import java.util.List;

public class InstructorService {

    private List<Instructor> instructors = new ArrayList<>();
    private int nextID = 1;

    public void addInstructor(String firstName, String lastName, String phoneNumber, String email){
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
            System.out.printf("%-10s | %-20s | %-20s | %-30s\n", "ID" , "First Name", "Second Name", "Email");
            System.out.println("----------------------------------------------------------------------------");
            for (Instructor instructor : instructors) {
                System.out.printf("%-10s | %-20s | %-20s | %-30s\n",
                        instructor.getInstructorID(),
                        instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getEmail());
            }
            System.out.println();
        }
    }


}
