package com.centegy.sms.service;

import com.centegy.sms.model.Course;
import com.centegy.sms.model.Instructor;

import java.util.ArrayList;
import java.util.List;



public class CourseService {

    private InstructorService instructorService;


    public CourseService(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

   public List<Course> courses = new ArrayList<>();

   private static int nextID = 1;
    public void addCourses(String courseName, String courseCode, String credits, Instructor instructorID){

        if (instructorID == null) {
            System.out.println("Warning: Course created without an assigned instructor.");
        }
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                System.out.println("Error: A Course with code " + courseCode + " already exists.");
                return;
            }
        }

        Course course = new Course(
                nextID++,
                courseName,
                courseCode,
                credits,
                instructorID
        );
        courses.add(course);
    }


    public Course findCourseByID(int courseID){

        for (Course c : courses){
            if (c.getCourseID() == courseID){
                return c;
            }
        }

        return null;
    }

    public Course findCourseByCode(String courseCode){
        for (Course c : courses){
            if (c.getCourseCode().equalsIgnoreCase(courseCode)){
                return c;
            }
        }
        return null;
    }

    public boolean removeCourse (int courseID){
        return courses.removeIf(c -> c.getCourseID() == courseID);
    }

    public Course updateCourse(int courseID, int choice, String newValue) {
        for (Course c : courses) {
            if (c.getCourseID() == courseID) {
                switch (choice) {
                    case 1: {
                        c.setCourseName(newValue);
                        break;
                    }
                    case 2: {
                        try {
                            int instructorID = Integer.parseInt(newValue);

                            Instructor instructor = instructorService.findInstructor(instructorID);

                            if (instructor != null) {
                                c.setInstructorID(instructor);
                                System.out.println("Instructor " + instructor.getFirstName() + " assigned to course!");
                            } else {
                                System.out.println("Error: Instructor ID " + instructorID + " does not exist in the system.");
                                return null;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: '" + newValue + "' is not a valid numeric ID.");
                            return null;
                        }
                        break;
                    }
                    case 3: {
                        c.setCredits(newValue);
                        break;
                    }
                    case 0: {
                        System.out.println("Exiting update...");
                        break;
                    }
                    default:
                        System.out.println("Invalid Choice");
                        return null;
                }
                System.out.println("Update Successful");
                return c;
            }
        }
        System.out.println("Course with ID doesn't exist");
        return null;
    }

    public boolean assignInstructor(int courseID, Instructor instructor) {
        for (Course c : courses) {
            if (c.getCourseID() == courseID) {
                c.setInstructorID(instructor);
                return true;
            }
        }
        return false;
    }

    public void showCourses (){

        if (courses.isEmpty()){
            System.out.println("No record available");
        }
        else {
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("%-5s | %-35s | %-15s | %-8s | %-20s \n", "ID" , "Course Name", "Course Code", "Credits", "Instructor");
            System.out.println("----------------------------------------------------------------------------");
            for (Course course : courses) {
                System.out.printf("%-5s | %-35s | %-15s | %-8s | %-20s\n",
                        course.getCourseID(),
                        course.getCourseName(),
                        course.getCourseCode(),
                        course.getCredits(),
                        course.getInstructorID() != null ? course.getInstructorID().getFirstName() : "Unassigned");
            }
        }
    }
}
