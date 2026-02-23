package com.centegy.sms.service;

import com.centegy.sms.model.*;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentService {

    private StudentService studentService;
    private CourseService courseService;

    private List<Enrolment> enrolments = new ArrayList<>();

    public List<Enrolment> getAllEnrolments() {
        return this.enrolments;
    }

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

    public boolean isStudentEnrolled(int studentID, int courseID) {
        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID &&
                    e.getCourse().getCourseID() == courseID) {
                return true;
            }
        }
        return false;
    }

    public Grade getGradeFromScore(int score) {
        if (score >= 90) return Grade.A_PLUS;
        if (score >= 85) return Grade.A;
        if (score >= 80) return Grade.A_MINUS;
        if (score >= 75) return Grade.B_PLUS;
        if (score >= 71) return Grade.B;
        if (score >= 68) return Grade.B_MINUS;
        if (score >= 64) return Grade.C_PLUS;
        if (score >= 61) return Grade.C;
        if (score >= 57) return Grade.C_MINUS;
        if (score >= 53) return Grade.D_PLUS;
        if (score >= 45) return Grade.D;
        return Grade.FAILS;
    }

    public void assignMarks(int studentID, int courseID, int score) {
        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID && e.getCourse().getCourseID() == courseID) {
                e.setNumericScore(score);
                Grade calculatedGrade = getGradeFromScore(score);
                e.setGrade(calculatedGrade);
                System.out.println("Marks Assigned: " + score);
                System.out.println("Grade: " + calculatedGrade + " (GPA: " + calculatedGrade.getPoints() + ")");
                return;
            }
        }
        System.out.println("No enrollment found for this Student in this Course.");
    }

    public double calculateStudentGPA(int studentID) {
        double totalWeightedPoints = 0;
        int totalCredits = 0;

        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID && e.getGrade() != Grade.N_A) {
                int credits = parseCredits(e.getCourse().getCredits());
                totalWeightedPoints += (e.getGrade().getPoints() * credits);
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) return 0.0;
        return totalWeightedPoints / totalCredits;
    }

    public int parseCredits(String creditString) {
        String[] parts = creditString.split("\\+");
        int total = 0;
        for (String part : parts) {
            total += Integer.parseInt(part.trim());
        }
        return total;
    }

    public void showTranscript(int studentID, StudentService studentService) {
        Student s = studentService.findStudent(studentID);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n------------------------------------------------");
        System.out.println("              ACADEMIC TRANSCRIPT");
        System.out.println("Student: " + s.getFirstName() + " " + s.getLastName());
        System.out.println("----------------------------------------------------");
        System.out.printf("%-30s | %-7s | %-5s\n", "Course", "Grade", "Marks");
        System.out.println("----------------------------------------------------");

        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID) {
                System.out.printf("%-30s | %-7s | %-5.1f\n",
                        e.getCourse().getCourseName(),
                        e.getGrade(),
                        e.getGrade().getPoints());
            }
        }

        double finalGPA = calculateStudentGPA(studentID);
        System.out.println("\n----------------------------------------");
        System.out.printf("FINAL CGPA: %.2f\n", finalGPA);
        System.out.println("----------------------------------------\n");
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
                System.out.println("Student ID: " + s.getStudentID() + " | Name: " + s.getFirstName() + " " + s.getLastName());
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
