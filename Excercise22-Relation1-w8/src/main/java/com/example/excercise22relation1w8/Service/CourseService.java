package com.example.excercise22relation1w8.Service;

import com.example.excercise22relation1w8.Api.ApiException;
import com.example.excercise22relation1w8.Model.Course;
import com.example.excercise22relation1w8.Model.Teacher;
import com.example.excercise22relation1w8.Repository.CourseRepository;
import com.example.excercise22relation1w8.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Integer teacher_id, Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer course_id, Course course) {
        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw new ApiException("Course not found");
        }
        course1.setName(course.getName());
        courseRepository.save(course1);
    }

    public void deleteCourse(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        if (course.getTeacher() != null) {
            course.setTeacher(null);
            courseRepository.save(course);
        }
        courseRepository.delete(course);
    }

    public String teacherNameByCourse(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        Teacher teacher = course.getTeacher();
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        return teacher.getName();
    }
}
