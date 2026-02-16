package com.centegy.sms.service;

import com.centegy.sms.model.*;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentService {

    private StudentService studentService;
    private CourseService courseService;

    private List<Enrolment> enrolments = new ArrayList<>();

    private static int newID = 1;

    public EnrolmentService(StudentService studentService, CourseService courseService){

        this.studentService = studentService;
        this.courseService = courseService;

    }

    public void enrolStudent(int studentID, int courseID){

        Student s = studentService.findStudent(studentID);
        Course c = courseService.findCourseByID(courseID);


        if (s == null){
            System.out.println("Error: StudentID " + studentID + " Not found");
            return;
        }

        if (c == null)
        {
            System.out.println("Error: CourseID " + courseID + "Not found");
            return;
        }

        for (Enrolment e : enrolments){
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID){
                System.out.println("Error: This student is already enroled in this course.");
                return;
            }
        }

        Enrolment newEnrolment = new Enrolment(newID++, s, c );
        enrolments.add(newEnrolment);

    }

    public void unenrolStudent(int studentID, int courseID){
        for (Enrolment e : enrolments){
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID){
                enrolments.remove(e);
                return;
            }
        }
        System.out.println("No student found with such record");
    }

    public void assignGrade(int studentID, int courseID, Grade grade){
        for (Enrolment e : enrolments){
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID){
                e.setGrade(grade);
                System.out.println("Grade assigned Successfully");
                return;
            }
        }
        System.out.println("No student found with such record");
    }


    public Enrolment findEnrolment(int studentID, int courseID){

        for (Enrolment e : enrolments){
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID){
                return e;
            }
        }

        return null;
    }

    public void showEnrolments (){

        if (enrolments.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-30s | %-25s | %-8s | %-15s\n", "ID" , "Name", "Enrolment Start Date", "Grade", "Course");
            System.out.println("-------------------------------------------------------------------------------------------");
            for (Enrolment e : enrolments) {
                System.out.printf("%-5s | %-30s | %-25s | %-8s | %-15s\n",
                        e.getStudent().getStudentID(),
                        e.getStudent().getFirstName() + " " + e.getStudent().getLastName(),
                        e.getEnrolmentStartDate() != null ? e.getEnrolmentStartDate() : "N/A",
                        e.getGrade() != null ? e.getGrade() : "N/A",
                        e.getCourse().getCourseName() != null ? e.getCourse().getCourseName() : "N/A");
            }
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }



}
