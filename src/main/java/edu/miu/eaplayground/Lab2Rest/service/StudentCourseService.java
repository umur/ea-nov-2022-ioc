package edu.miu.eaplayground.Lab2Rest.service;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import edu.miu.eaplayground.Lab2Rest.entity.Student;

import java.util.List;

public interface StudentCourseService {

    public void assignCourse(int studentId, int courseId);
    public List<Student> getStudentByMajor(String major);
    public List<Course> getCourseByStudentId(int id);

}
