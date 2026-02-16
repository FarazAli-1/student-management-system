package com.centegy.sms;

import com.centegy.sms.model.*;
import com.centegy.sms.service.CourseService;
import com.centegy.sms.service.EnrolmentService;
import com.centegy.sms.service.InstructorService;
import com.centegy.sms.service.StudentService;
import com.centegy.sms.utils.ValidationUtils;

import java.util.List;
import java.util.Scanner;

public class MenuHandler {

    static StudentService studentService = new StudentService();
    static InstructorService instructorService = new InstructorService();
    static CourseService courseService = new CourseService(instructorService);
    static EnrolmentService enrolmentService = new EnrolmentService(studentService, courseService);
    static Scanner sc = new Scanner(System.in);


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
            System.out.println("3 - Update a Student's Information");
            System.out.println("4 - Find Student by ID");
            System.out.println("5 - Find Student by Name");
            System.out.println("6 - List all Students");
            System.out.println("0 - Return to Main Menu");

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

                    String phoneNumber;
                    while (true){
                        System.out.print("Enter Phone Number : ");
                        phoneNumber = sc.nextLine();
                        if (ValidationUtils.isValidPhoneNumber(phoneNumber)){
                            break;
                        } else {
                            System.out.println("Invalid Phone Number format. Please try again.");
                        }
                    }
                    String email;
                    while (true) {
                        System.out.print("Enter Email: ");
                        email = sc.nextLine();
                        if (ValidationUtils.isValidEmail(email)) {
                            break;
                        } else {
                            System.out.println("Invalid email format (e.g., name@example.com). Please try again.");
                        }
                    }
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
                case 3:{

                    boolean running = true;
                    while (running){
                        System.out.println("Enter the ID of the Student whose information you want to update.");
                        System.out.print("Student ID : ");
                        int sID = sc.nextInt();
                        if (sID == 0) {
                            running = false;
                            break;
                        }
                        System.out.println("Which information do you want to update?");
                        System.out.println("1 - Update First Name");
                        System.out.println("2 - Update Last Name");
                        System.out.println("3 - Update Phone Number");
                        System.out.println("4 - Update Email");
                        System.out.println("0 - Exit");
                        System.out.print("Enter your choice : ");
                        int c = sc.nextInt();
                        sc.nextLine();
                        if (c == 0) {
                            running = false;
                            break;
                        }
                        System.out.println();
                        System.out.print("Enter the new information: ");
                        String newValue = sc.nextLine();
                        studentService.updateStudent(sID , c , newValue);
                        System.out.println("Would you like to update another student? (1 for Yes, 0 for No)");
                        if (sc.nextInt() == 0) {
                            System.out.println("Returning to Main Menu...");
                            running = false;
                        }

                    }
                    break;
                }

                case 4: {

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
                case 5:{
                    System.out.print("Enter Student Name : ");
                    sc.nextLine();
                    String studentName = sc.nextLine();
                    List<Student> foundStudents = studentService.searchByName(studentName);

                    if (foundStudents.isEmpty()) {
                        System.out.println("No Student found with that name.");
                    } else {
                        System.out.println("Found " + foundStudents.size() + " Students");
                        for (Student s : foundStudents){
                            System.out.println(s);
                        }
                    }
                    break;
                }
                case 6: {
                    studentService.showStudents();
                    break;
                }

                case 0:
                    System.out.println("Returning to main menu...");
                    cond = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void courseMenu() {

        boolean cond = true;

        while (cond) {
            System.out.println("--------------COURSE MANAGEMENT MENU--------------");
            System.out.println("1 - Add a Course");
            System.out.println("2 - Remove a Course");
            System.out.println("3 - Update a Course");
            System.out.println("4 - Find course by ID");
            System.out.println("5 - Find course by code");
            System.out.println("6 - List all Courses");
            System.out.println("7 - Assign a Instructor to a Course");
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

                    if (courseService.removeCourse(cID)) {
                        System.out.println("Course successfully removed");
                    } else {
                        System.out.println("No Course exists with that ID");
                    }
                    break;
                }
                case 3: {
                    boolean running = true;
                    while (running) {
                        System.out.print("Enter Course ID to update: ");
                        int cID = sc.nextInt();

                        System.out.println("Which information do you want to update?");
                        System.out.println("1 - Update Course Name");
                        System.out.println("2 - Update Instructor (Write ID)");
                        System.out.println("3 - Update Course Credits");
                        System.out.println("0 - Exit");
                        System.out.print("Enter your choice: ");
                        int c = sc.nextInt();
                        sc.nextLine();

                        if (c == 0) {
                            running = false;
                            break;
                        }

                        System.out.print("Enter the new information: ");
                        String newValue = sc.nextLine();

                        courseService.updateCourse(cID, c, newValue);

                        System.out.println("Would you like to update another course? (1 for Yes, 0 for No)");
                        if (sc.nextInt() == 0) {
                            System.out.println("Returning to main menu...");
                            running = false;
                        }
                        sc.nextLine();
                    }
                    break;
                }
                case 4: {
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
                case 5: {
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

                case 6: {
                    courseService.showCourses();
                    break;
                }

                case 7: {
                    sc.nextLine();
                    System.out.print("Enter the Course ID : ");
                    int courseID = sc.nextInt();
                    System.out.print("Enter the Instructor ID : ");
                    int instructorID = sc.nextInt();

                    Instructor instructor = instructorService.findInstructor(instructorID);
                    if (instructor != null) {
                        boolean success = courseService.assignInstructor(courseID, instructor);
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
                    System.out.println("Returning to main menu...");
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

                    String phoneNumber;
                    while (true){
                        System.out.print("Enter Phone Number : ");
                        phoneNumber = sc.nextLine();
                        if (ValidationUtils.isValidPhoneNumber(phoneNumber)){
                            break;
                        } else {
                            System.out.println("Invalid Phone Number format. Please try again.");
                        }
                    }
                    String email;
                    while (true) {
                        System.out.print("Enter Email: ");
                        email = sc.nextLine();
                        if (ValidationUtils.isValidEmail(email)) {
                            break;
                        } else {
                            System.out.println("Invalid email format (e.g., name@example.com). Please try again.");
                        }
                    }

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
                    System.out.println("Returning to main menu...");
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
            System.out.println("4 - Find Enrolment");
            System.out.println("5 - Show all enrolments");
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

                    Student s = studentService.findStudent(sID);
                    Course c = courseService.findCourseByID(cID);

                    if (s != null && c != null){
                        enrolmentService.enrolStudent(sID,cID);
                    }
                        else {
                            System.out.println("Error: Student or Course ID not found.");
                        }
                        break;
                }
                case 2: {
                    System.out.println("Enter Student ID of student you want to unenrol");
                    int sID = sc.nextInt();
                    System.out.println("Enter Course ID from which you want the student to be unenroled");
                    int cID = sc.nextInt();
                    Student s = studentService.findStudent(sID);
                    Course c = courseService.findCourseByID(cID);

                    if (s != null && c != null){
                        enrolmentService.unenrolStudent(sID,cID);
                    }
                    else {
                        System.out.println("Error: Student or Course ID not found.");
                    }
                    break;
                }
                case 3:{
                    System.out.print("Enter Student ID: ");
                    int sID = sc.nextInt();
                    System.out.print("Enter Course ID: ");
                    int cID = sc.nextInt();
                    System.out.print("Enter Grade (A, B, C, D, F): ");
                    String gInput = sc.nextLine().toUpperCase().trim();

                    try {
                        Grade g = Grade.valueOf(gInput);
                        enrolmentService.assignGrade(sID, cID, g);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid Grade entered.");
                    }
                    break;
                }
                case 4:{
                    System.out.print("Enter Student ID: ");
                    int sID = sc.nextInt();
                    System.out.print("Enter Course ID: ");
                    int cID = sc.nextInt();
                    Enrolment enrolment = enrolmentService.findEnrolment(sID,cID);
                    if (enrolment == null) {
                        System.out.println("Student or Course not found.");
                    } else {
                        System.out.println("Enrolment Found:");
                        System.out.println(enrolment);
                    }
                    break;

                }
                case 5:{
                    enrolmentService.showEnrolments();
                    break;
                }
                case 0:{
                    System.out.println("Returning to main menu...");
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

    static void initializeData() {

        studentService.addStudent("Faraz", "Ali", "0309-1266745", "faraz.ali@centegytechnologies.com");
        studentService.addStudent("Mohammad", "Umar", "0316-2389923", "M.Umar@gmail.com");
        studentService.addStudent("Mohammad", "Ali", "0308-9781378", "Muhammad.ali@outlook.com");
        studentService.addStudent("Sara", "Khan", "0321-1234567", "sara.khan@gmail.com");
        studentService.addStudent("Zain", "Ahmed", "0345-9876543", "zain.ahmed@yahoo.com");
        studentService.addStudent("Ali", "Ahmed", "0365-9876543", "ali.ahmed@yahoo.com");


        instructorService.addInstructor("Badar", "Sami", "0333-2938274", "Badar.sami@uok.com");
        instructorService.addInstructor("Dr. Asim", "Ali", "0321-2882829", "Asim.ali@uok.com");
        instructorService.addInstructor("Dr. Ayesha", "Rehman", "0300-1122334", "ayesha.rehman@uok.com");

        courseService.addCourses("Object Oriented Programming", "BSCS-401", "3", instructorService.findInstructor(1));
        courseService.addCourses("Management Information System", "BSCS-502", "3", instructorService.findInstructor(2));
        courseService.addCourses("Data Structures", "BSCS-402", "3", instructorService.findInstructor(3));
        courseService.addCourses("Database Systems", "BSCS-403", "3", instructorService.findInstructor(1));

        enrolmentService.enrolStudent(1, 1); // Faraz in OOP
        enrolmentService.enrolStudent(1, 4); // Faraz in Database
        enrolmentService.enrolStudent(2, 1); // Umar in OOP
        enrolmentService.enrolStudent(3, 2); // Ali in MIS
        enrolmentService.enrolStudent(4, 3); // Sara in Data Structures
        enrolmentService.enrolStudent(5, 3); // Zain in Data Structures

    }

}
