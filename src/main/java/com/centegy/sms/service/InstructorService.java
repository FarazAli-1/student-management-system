package com.centegy.sms.service;

import com.centegy.sms.model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorService {

    private final List<Instructor> instructors = new ArrayList<>();

    public List<Instructor> getAllInstructors() {
        return this.instructors;
    }

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

    public List<Instructor> searchByName(String name){
        List<Instructor> results = new ArrayList<>();
        String searchLower = name.toLowerCase().trim();

        for (Instructor i : instructors){
            String firstName = i.getFirstName().toLowerCase();
            String lastName = i.getLastName().toLowerCase();
            String fullName = firstName + " " + lastName;
            if (firstName.contains(searchLower) ||
                    lastName.contains(searchLower) ||
                    fullName.contains(searchLower)) {
                results.add(i);
            }
        }
        return results;
    }

    public boolean removeInstructor (int instructorID){
        return instructors.removeIf(i -> i.getInstructorID() == instructorID);
    }

    public void updateInstructor(int instructorID, int choice, String newValue) {
        for (Instructor i : instructors) {
            if (i.getInstructorID() == instructorID) {
                switch (choice) {
                    case 1 : {
                        i.setFirstName(newValue);
                        break;
                    }
                    case 2 :{
                        i.setLastName(newValue);
                        break;
                    }
                    case 3 :{
                        i.setPhoneNumber(newValue);
                        break;
                    }
                    case 4 :{
                        i.setEmail(newValue);
                        break;
                    }
                    case 0 : {
                        return;
                    }
                    default :
                    {
                        System.out.println("Invalid Choice");
                        return;
                    }
                }
                System.out.println("Update Successful");
                return;
            }
        }
        System.out.println("Instructor with ID " + instructorID + " doesn't exist");
    }

    public void showInstructors (){

        if (instructors.isEmpty()){
            System.out.println("No record available");
        }
        else {
            List<Instructor> sortedList = new ArrayList<>(instructors);
            sortedList.sort((s1,s2) -> {
                int res = s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
                if (res != 0) {
                    return res;
                }
                return s1.getLastName().compareToIgnoreCase(s2.getLastName());
            });

            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("%-5s | %-25s | %-15s | %-30s\n", "ID" , "Name", "Phone Number", "Email");
            System.out.println("----------------------------------------------------------------------------");
            for (Instructor instructor : sortedList) {
                System.out.printf("%-5s | %-25s | %-15s | %-30s\n",
                        instructor.getInstructorID(),
                        instructor.getFirstName() + " " + instructor.getLastName(),
                        instructor.getPhoneNumber(),
                        instructor.getEmail());
            }
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
        }
    }


}
