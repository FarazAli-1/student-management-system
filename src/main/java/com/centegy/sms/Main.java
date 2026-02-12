package com.centegy.sms;

import com.centegy.sms.model.Course;
import com.centegy.sms.model.Instructor;
import com.centegy.sms.model.Student;
import com.centegy.sms.service.CourseService;
import com.centegy.sms.service.EnrolmentService;
import com.centegy.sms.service.InstructorService;
import com.centegy.sms.service.StudentService;

import java.util.Scanner;

public class Main {

    static StudentService studentService = new StudentService();
    static InstructorService instructorService = new InstructorService();
    static CourseService courseService = new CourseService();
    static EnrolmentService enrolmentService = new EnrolmentService(studentService, courseService);
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        initializeData();
        boolean cond = true;
        while (cond) {

        mainMenu();

        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    studentMenu();
                    break;

                case 2:
                    courseMenu();
                    break;

                case 3:
                    instructorMenu();
                    break;

                case 4:
                    enrolmentMenu();
                    break;

                case 0:
                    System.out.println("Thanks");
                    cond = false;
                    break;
            }
        }
    }

    static void mainMenu(){
        System.out.println("--------------STUDENT MANAGEMENT SYSTEM--------------");
        System.out.println("1 - Student Management Menu");
        System.out.println("2 - Course Management Menu");
        System.out.println("3 - Instructor Management Menu");
        System.out.println("4 - Enrolment Management Menu");
        System.out.println("0 - Exit");
    }

    static void studentMenu() {

        boolean cond = true;

        while (cond){

            System.out.println();
        System.out.println("--------------STUDENT MANAGEMENT MENU--------------");
        System.out.println("1 - Add a Student");
        System.out.println("2 - Remove a Student");
        System.out.println("3 - Find Student");
        System.out.println("4 - List all Students");
        System.out.println("0 - Back to Main Menu");

            System.out.println();
        System.out.print("Enter : ");

        int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the details of the student you want to add");
                    sc.nextLine();
                    System.out.print("Enter First Name : ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name : ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Phone Number : ");
                    String phoneNumber = sc.nextLine();
                    System.out.print("Enter Email : ");
                    String email = sc.nextLine();

                    studentService.addStudent(firstName, lastName, phoneNumber, email);
                    System.out.println("Student successfully added");
                    break;
                }
                case 2: {
                    System.out.println("Enter the ID of the student you want to remove");
                    int sID = sc.nextInt();

                    if (studentService.removeStudent(sID)){
                        System.out.println("Student successfully removed");
                    }
                    else {
                        System.out.println("No Student exists with that ID");
                    }


                    break;
                }
                case 3: {
                    System.out.println("Enter Student ID");
                    int sID = sc.nextInt();
                    Student student = studentService.findStudent(sID);

                    if (student == null) {
                        System.out.println("Student not found.");
                    } else {
                        System.out.println("Student Found:");
                        System.out.println(student);
                    }
                    break;
                }
                case 4: {
                    studentService.showStudents();
                    break;
                }

                case 0:
                    System.out.println("Thank You");
                    cond = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void courseMenu() {

        boolean cond = true;

        while (cond){
            System.out.println("--------------COURSE MANAGEMENT MENU--------------");
            System.out.println("1 - Add a Course");
            System.out.println("2 - Remove a course");
            System.out.println("3 - Find course by ID");
            System.out.println("4 - Find course by code");
            System.out.println("5 - List all Courses");
            System.out.println("6 - Assign a Instructor to a Course");
            System.out.println("0 - Back to Main Menu");


            System.out.print("Enter : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the details of the course you want to add");
                    sc.nextLine();
                    System.out.print("Enter Course Name : ");
                    String courseName = sc.nextLine();
                    System.out.print("Enter Course Code : ");
                    String courseCode = sc.nextLine();
                    System.out.print("Enter credits : ");
                    String credits = sc.nextLine();

                    System.out.print("Enter Instructor ID (or 0 if unknown): ");
                    int insID = sc.nextInt();
                    sc.nextLine();

                    Instructor instructor = instructorService.findInstructor(insID);

                    if (instructor == null && insID != 0) {
                        System.out.println("Warning: Instructor ID " + insID + " not found. Creating course as 'Unassigned'.");
                    }


                    courseService.addCourses(courseName, courseCode, credits, instructor);
                    System.out.println("Course successfully added!");
                    break;
                }

                case 2: {
                    System.out.println("Enter the ID of the Course you want to remove");
                    int cID = sc.nextInt();

                    if (courseService.removeCourse(cID)){
                        System.out.println("Course successfully removed");
                    }
                    else {
                        System.out.println("No Course exists with that ID");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter Course ID");
                    int cID = sc.nextInt();
                    Course course = courseService.findCourseByID(cID);

                    if (course == null) {
                        System.out.println("Course not found.");
                    } else {
                        System.out.println("Course Found:");
                        System.out.println(course);
                    }
                    break;
                }
                case 4:{
                    System.out.println("Enter Course Code");
                    sc.nextLine();
                    String courseCode = sc.nextLine();
                    Course course = courseService.findCourseByCode(courseCode);

                    if (course == null) {
                        System.out.println("Course not found.");
                    } else {
                        System.out.println("Course Found:");
                        System.out.println(course);
                    }
                    break;
                }

                case 5: {
                    courseService.showCourses();
                    break;
                }

                case 6:{
                    sc.nextLine();
                    System.out.print("Enter the Course ID : ");
                    int courseID = sc.nextInt();
                    System.out.print("Enter the Instructor ID : ");
                    int instructorID = sc.nextInt();

                    Instructor instructor = instructorService.findInstructor(instructorID);
                    if (instructor !=null){
                        boolean success = courseService.assignInstructor(courseID,instructor);
                        if (success) {
                            System.out.println("Instructor " + instructor.getFirstName() + " assigned to course!");
                        } else {
                            System.out.println("Error: Course ID " + courseID + " not found.");
                        }
                    } else {
                        System.out.println("Error: Instructor ID " + instructorID + " does not exist.");
                    }
                    break;


                }
                case 0:
                    System.out.println("Thank You");
                    cond = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void instructorMenu() {

        boolean cond = true;

        while (cond){
            System.out.println("--------------INSTRUCTOR MANAGEMENT MENU--------------");
            System.out.println("1 - Add a Instructor");
            System.out.println("2 - Remove a Instructor");
            System.out.println("3 - Find Instructor");
            System.out.println("4 - List all Instructors");
            System.out.println("0 - Back to Main Menu");



            System.out.print("Enter : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the details of the instructor you want to add");
                    sc.nextLine();
                    System.out.print("Enter First Name : ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name : ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Phone Number : ");
                    String phoneNumber = sc.nextLine();
                    System.out.print("Enter Email : ");
                    String email = sc.nextLine();

                    instructorService.addInstructor(firstName, lastName, phoneNumber, email);
                    System.out.println("Instructor successfully added");
                    break;
                }
                case 2: {
                    System.out.println("Enter the ID of the instructor you want to remove");
                    int sID = sc.nextInt();

                    if (instructorService.removeInstructor(sID)){
                        System.out.println("Instructor successfully removed");
                    }
                    else {
                        System.out.println("No Instructor exists with that ID");
                    }

                    break;
                }
                case 3: {
                    System.out.println("Enter Instructor ID");
                    int insID = sc.nextInt();
                    Instructor instructor = instructorService.findInstructor(insID);

                    if (instructor == null) {
                        System.out.println("Instructor not found.");
                    } else {
                        System.out.println("Instructor Found:");
                        System.out.println(instructor);
                    }
                    break;
                }
                case 4: {
                    instructorService.showInstructors();
                    break;
                }

                case 0:
                    System.out.println("Thank You");
                    cond = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void enrolmentMenu(){

        boolean cond = true;
        while (cond){
            System.out.println("--------------ENROLMENT MANAGEMENT MENU--------------");
            System.out.println("1 - Enrol Student in Course");
            System.out.println("2 - Unenrol Student from Course");
            System.out.println("3 - Assign/Update Grade");
            System.out.println("4 - List all Enrolments");
            System.out.println("0 - Back to Main Menu");

            System.out.println();
            System.out.print("Enter : ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:{
                    System.out.println("Enter Student ID of student you want to enrol");
                    int sID = sc.nextInt();
                    System.out.println("Enter Course ID in which you want the student to be enrolled");
                    int cID = sc.nextInt();
                    enrolmentService.enrolStudent(sID,cID);
                    System.out.println("Student with student ID : " + sID + "( " + studentService.findStudent(sID).getFirstName() + " )" + " successfully enrolled in " + courseService.findCourseByID(cID).getCourseName());
                    break;
                }
                case 2: {
                    System.out.println("Enter Student ID of student you want to unenrol");
                    int sID = sc.nextInt();
                    System.out.println("Enter Course ID from which you want the student to be unenrolled");
                    int cID = sc.nextInt();
                    enrolmentService.unenrolStudent(sID, cID);
                    System.out.println("Student with student ID : " + sID + "(" + studentService.findStudent(sID).getFirstName() + ")" +  " successfully unenrolled from " + courseService.findCourseByID(cID).getCourseName());
                    break;
                }
                case 3:{

                    break;
                }
                case 4:{
                    enrolmentService.showEnrolments();
                    break;
                }
                case 0:{
                    System.out.println("Thank you");
                    cond = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice");
                    cond = false;
                    break;

            }
        }

    }


    static void initializeData(){
        studentService.addStudent("Faraz", "Ali", "0309-1266745", "faraz.ali@centegytechnologies.com");
        studentService.addStudent("Mohammad", "Umar", "0316-2389923", "M.Umar@gmail.com");
        studentService.addStudent("Mohammad", "Ali", "0308-9781378", "Muhammad.ali@outlook.com");
        instructorService.addInstructor("Badar", "Sami", "0333-2938274", "Badar.sami@uok.com");
        instructorService.addInstructor("Asim", "Ali", "0321-2882829", "Asim.ali@uok.com");
        courseService.addCourses("Object Oriented Programming", "BSCS-401", "3",instructorService.findInstructor(1));
        courseService.addCourses("Management Information System", "BSCS-502", "3", instructorService.findInstructor(2));

    }

}
