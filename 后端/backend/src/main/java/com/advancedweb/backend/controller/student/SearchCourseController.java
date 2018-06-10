package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.controller.teacher.TeacherCoursesController;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCourseController {
    @Autowired
    private CourseRepository cr;

    @RequestMapping(value = "/search_course", method = RequestMethod.GET)
    public Course_json[] student_courses() {
        Course[] courses= cr.findAllCourses();

        return TeacherCoursesController.getJsonModel(courses);
    }


}