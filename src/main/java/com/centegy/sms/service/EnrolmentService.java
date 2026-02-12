package com.centegy.sms.service;

import com.centegy.sms.model.Course;
import com.centegy.sms.model.Enrolment;
import com.centegy.sms.model.Grade;
import com.centegy.sms.model.Student;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentService {

    private StudentService studentService;
    private CourseService courseService;

    private List<Enrolment> enrolments = new ArrayList<>();

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

        int newID = enrolments.size() + 1;
        Enrolment newEnrolment = new Enrolment(newID, s, c );
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

    public void showEnrolments (){

        if (enrolments.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-20s | %-15s | %-8s | %-15s\n", "ID" , "Enrolemnt Start Date", "Course End Date", "Grade", "Course");
            System.out.println("-------------------------------------------------------------------------------------------");
            for (Enrolment enrolment : enrolments) {
                System.out.printf("%-5s | %-20s | %-15s | %-8s | %-15s\n",
                        enrolment.getEnrolmentID(),
                        enrolment.getEnrolmentStartDate(),
                        enrolment.getEndDate(),
                        enrolment.getGrade(),
                        enrolment.getCourse().getCourseName());
            }
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }



}
