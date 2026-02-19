package com.centegy.sms.service;

import com.centegy.sms.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        return this.students;
    }

    private static int nextID = 1;

    public void addStudent(String firstName, String lastName, String phoneNumber, String email){

        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Error: A student with email " + email + " already exists.");
                return;
            }
        }
        Student student = new Student(
                nextID++,
                firstName,
                lastName,
                phoneNumber,
                email
        );

        students.add(student);

    }

    public Student findStudent(int studentID){

            for (Student s : students){
                if (s.getStudentID() == studentID){
                    return s;
                }
            }

        return null;
    }


    public List<Student> searchByName(String name){
        List<Student> results = new ArrayList<>();
        for (Student s : students){
            if (s.getFirstName().equalsIgnoreCase(name) || s.getLastName().equalsIgnoreCase(name)){
                results.add(s);
            }
        }
        return results;
    }

    public boolean removeStudent (int studentID, EnrolmentService enrolmentService){
        boolean removed = students.removeIf(s -> s.getStudentID() == studentID);
        if (removed) {
            enrolmentService.removeAllEnrolmentsByStudent(studentID);
        }
        return removed;
    }

    public Student updateStudent(int studentID, int choice,String newValue){
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                switch (choice) {
                    case 1: {
                        s.setFirstName(newValue);
                        break;
                    }
                    case 2: {
                        s.setLastName(newValue);
                        break;
                    }
                    case 3: {
                        s.setPhoneNumber(newValue);
                        break;
                    }
                    case 4: {
                        s.setEmail(newValue);
                        break;
                    }
                    case 0: {
                        System.out.println("Thank You!");
                        return s;
                    }
                    default:
                        System.out.println("Invalid Choice");
                }
                System.out.println("Update Successful");
                return s;
            }

        }
        System.out.println("Student with ID doesn't exist");
        return null;
    }

    public void showStudents (){

        if (students.isEmpty()){
            System.out.println("No record available");
        }
        else {

            List<Student> sortedList = new ArrayList<>(students);

            sortedList.sort((s1,s2) -> {
                int res = s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
                if (res != 0) {
                    return res;
                }
                return s1.getLastName().compareToIgnoreCase(s2.getLastName());
            });

            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-30s | %-15s | %-30s\n", "ID" , "Name", "Phone Number" ,"Email");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Student student : sortedList) {
                System.out.printf("%-5s | %-30s | %-15s | %-30s\n",
                        student.getStudentID(),
                        student.getFirstName() + " " + student.getLastName(),
                        student.getPhoneNumber(),
                        student.getEmail());
            }
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println();
        }
    }



}
