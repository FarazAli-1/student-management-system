package com.centegy.sms.service;

import com.centegy.sms.model.*;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentService {

    private final StudentService studentService;
    private final CourseService courseService;

    private final List<Enrolment> enrolments = new ArrayList<>();

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

    public static Grade getGradeFromScore(int score) {
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


    public void addExamResult(int sID, int cID, String name, double marks, double max) {
        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == sID && e.getCourse().getCourseID() == cID) {



                double currentTotal = 0;
                for (Exam exam : e.getExamList()) {
                    currentTotal += exam.getMarksObtained();
                }


                if (currentTotal + marks > 100) {
                    System.out.println("Error: Cannot add " + marks + " marks.");
                    System.out.println("Current total is " + currentTotal + ". This would exceed 100/100.");
                    return; // Stop the method here
                }

                // 3. If safe, add the exam and update
                e.getExamList().add(new Exam(name, marks, max));
                e.updateFinalGrade(); // Calls the method we wrote earlier

                System.out.println("Marks added. Total progress: " + e.getNumericScore() + "/100");
                return;
            }

        }
    }
    public double calculateStudentGPA(int studentID) {
        double totalWeightedPoints = 0;
        int totalCredits = 0;

        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID &&
                    e.getGrade() != Grade.N_A &&
                    e.getTotalMaxMarks() >= 100) {

                int credits = parseCredits(e.getCourse().getCredits());
                totalWeightedPoints += (e.getGrade().getPoints() * credits);
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) return 0.0; // Avoid division by zero
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

        System.out.println("\n====================================================");
        System.out.println("              ACADEMIC TRANSCRIPT");
        System.out.println("Student: " + s.getFirstName().toUpperCase() + " " + s.getLastName().toUpperCase());
        System.out.println("Student ID: " + s.getStudentID());
        System.out.println("====================================================");

        for (Enrolment e : enrolments) {
            if (e.getStudent().getStudentID() == studentID) {
                System.out.println("\nCOURSE: " + e.getCourse().getCourseName() + " [" + e.getCourse().getCourseCode() + "]");
                System.out.println("----------------------------------------------------");

                System.out.printf("%-15s | %-10s | %-10s\n", "Assessment", "Obtained", "Max Marks");
                System.out.println("--------------------------------------------------");

                if (e.getExamList().isEmpty()) {
                    System.out.println("  No exam records found for this course.");
                } else {
                    for (Exam exam : e.getExamList()) {
                        System.out.printf("  %-15s | %-10.1f | %-10.1f\n",
                                exam.getExamName(),
                                exam.getMaxPossible(),
                                exam.getMarksObtained(
                                ));
                    }
                }

                System.out.println("--------------------------------------------------");
                System.out.printf("%-15s : %-10d / 100\n", "TOTAL MARKS", e.getNumericScore());
                if (e.getTotalMaxMarks() < 100) {
                    System.out.printf("  %-15s : %-10s (INCOMPLETE - %d%% marked)\n",
                            "STATUS", "IN-PROGRESS", (int)e.getTotalMaxMarks());
                    System.out.printf("  %-15s : %-10s\n", "CURRENT GRADE", e.getGrade());
                } else {
                    System.out.printf("  %-15s : %-10s (GPA: %.2f)\n",
                            "FINAL GRADE", e.getGrade(), e.getGrade().getPoints());
                }
                System.out.println("----------------------------------------------------");
            }
        }

        double finalGPA = calculateStudentGPA(studentID);
        System.out.println("\n====================================================");
        System.out.printf("OVERALL CUMULATIVE GPA (CGPA): %.2f\n", finalGPA);
        System.out.println("\n====================================================");
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
