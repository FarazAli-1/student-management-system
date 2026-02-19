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

        if (getAvailableSeats(courseID) <= 0) {
            System.out.println("Course is full!");
            return;
        }

        Enrolment newEnrolment = new Enrolment(newID++, s, c );
        enrolments.add(newEnrolment);

    }

    public void unenrolStudent(int studentID, int courseID){

        boolean removed = enrolments.removeIf(e ->
                e.getStudent().getStudentID() == studentID &&
                        e.getCourse().getCourseID() == courseID
        );
        if (removed) {
            System.out.println("Student successfully unenroled.");
        } else {
            System.out.println("No student found with such record.");
        }


    }

    public void removeAllEnrolmentsByStudent(int studentID){
        String studentName = studentService.findStudent(studentID).getFirstName() + " " + studentService.findStudent(studentID).getLastName();
        enrolments.removeIf(e -> e.getStudent().getStudentID() == studentID);{
            System.out.println("Cleaned up all enrolment records for Student ID: " + studentID + studentName );
        }
    }

    public void removeAllEnrolmentsByCourse(int courseID){
        String courseName = courseService.findCourseByID(courseID).getCourseName();
        enrolments.removeIf(e -> e.getCourse().getCourseID() == courseID);{
            System.out.println("Cleaned up all enrolment records for Student ID: " + courseID + courseName );
        }
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

    public int countEnrolmentsByStudents(int studentID){
        int count = 0;
        for (Enrolment enrolment : enrolments){
            if (enrolment.getStudent().getStudentID() == studentID) count++;
        }
        return count;
    }

    public int countEnrolmentsByCourse(int courseID){
        int count = 0;
        for (Enrolment enrolment : enrolments){
            if (enrolment.getCourse().getCourseID() == courseID) count++;
        }
        return count;
    }



    public Enrolment findEnrolment(int studentID, int courseID){

        for (Enrolment e : enrolments){
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID){
                return e;
            }
        }

        return null;
    }

    public int getAvailableSeats(int courseID) {
        Course c = courseService.findCourseByID(courseID);
        int count = 0;
        for (Enrolment e : enrolments) {
            if (e.getCourse().getCourseID() == courseID) count++;
        }
        return c.getMaxCapacity() - count;
    }

    public void showCourseRoster(int courseID) {
        Course course = courseService.findCourseByID(courseID);
        if (course == null) {
            System.out.println("Error: Course not found.");
            return;
        }

        System.out.println("--- Roster for: " + course.getCourseName() + " ---");
        boolean found = false;

        for (Enrolment e : enrolments) {
            if (e.getCourse().getCourseID() == courseID) {
                Student s = e.getStudent();
                System.out.println("ID: " + s.getStudentID() + " | Name: " + s.getFirstName() + " " + s.getLastName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students enrolled in this course yet.");
        }
    }

    public void showStudentSchedule(int studentID) {
        Student student = studentService.findStudent(studentID);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }

        System.out.println("--- Schedule for: " + student.getFirstName() + " " + student.getLastName() + " ---");
        boolean found = false;

        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID) {
                Course c = e.getCourse();
                System.out.println("Course ID: " + c.getCourseID() + " | Name: " + c.getCourseName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("This student is not enrolled in any courses.");
        }
    }

    public void showEnrolments (){

        if (enrolments.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-30s | %-25s | %-8s | %-15s | %-15s \n", "ID" , "Name", "Enrolment Start Date", "Grade", "End Date" , "Course");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            for (Enrolment e : enrolments) {
                System.out.printf("%-5s | %-30s | %-25s | %-8s | %-15s | %-15s\n",
                        e.getStudent().getStudentID(),
                        e.getStudent().getFirstName() + " " + e.getStudent().getLastName(),
                        e.getEnrolmentStartDate() != null ? e.getEnrolmentStartDate() : "N/A",
                        e.getGrade() != null ? e.getGrade() : "N/A",
                        e.getEndDate(),
                        e.getCourse().getCourseName() != null ? e.getCourse().getCourseName() : "N/A");

            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        }
    }



}
