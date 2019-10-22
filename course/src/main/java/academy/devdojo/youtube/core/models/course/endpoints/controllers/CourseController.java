package academy.devdojo.youtube.core.models.course.endpoints.controllers;


import academy.devdojo.youtube.core.models.Course;
import academy.devdojo.youtube.core.models.course.endpoints.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/admin/course")
@RequiredArgsConstructor(onConstructor_ = {
        @Autowired
})
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCoruseList() {
        return new ResponseEntity(courseService.findAll(), HttpStatus.FOUND);
    }

}
