package com.centegy.sms.service;

import com.centegy.sms.model.Course;
import com.centegy.sms.model.CourseInstructor;
import com.centegy.sms.model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

   public List<Course> courses = new ArrayList<>();

   private int nextID = 1;
    public void addCourses(String courseName, String courseCode, String credits, Instructor instructorID){

        if (instructorID == null) {
            System.out.println("Warning: Course created without an assigned instructor.");
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
            System.out.printf("%-5s | %-35s | %-15s | %-8s\n", "ID" , "Course Name", "Course Code", "Credits");
            System.out.println("----------------------------------------------------------------------------");
            for (Course course : courses) {
                System.out.printf("%-5s | %-35s | %-15s | %-8s\n",
                        course.getCourseID(),
                        course.getCourseName(),
                        course.getCourseCode(),
                        course.getCredits());
            }
        }
    }
}
