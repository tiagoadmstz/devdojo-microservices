package academy.devdojo.youtube.core.models.course.endpoints.services;

import academy.devdojo.youtube.core.models.Course;
import academy.devdojo.youtube.core.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {
        @Autowired
})
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}