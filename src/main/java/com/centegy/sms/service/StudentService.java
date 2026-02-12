package com.centegy.sms.service;

import com.centegy.sms.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    private int nextID = 1;

    public void addStudent(String firstName, String lastName, String phoneNumber, String email){
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

    public boolean removeStudent (int studentID){
        return students.removeIf(s -> s.getStudentID() == studentID);
    }

    public void showStudents (){

        if (students.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-15s | %-15s | %-30s\n", "ID" , "First Name", "Second Name", "Email");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Student student : students) {
                System.out.printf("%-5s | %-15s | %-15s | %-30s\n",
                        student.getStudentID(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail());
            }
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println();
        }
    }
}
