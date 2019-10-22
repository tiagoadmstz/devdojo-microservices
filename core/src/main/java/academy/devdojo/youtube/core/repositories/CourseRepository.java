package academy.devdojo.youtube.core.repositories;


import academy.devdojo.youtube.core.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
