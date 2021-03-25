package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootdemo.models.Course;
import ru.itis.springbootdemo.models.Lesson;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByLessonsContains(Lesson lesson);

    @Query("select course from Course course inner join course.lessons lesson " +
            "where lesson.id = :lessonId")
    List<Course> findByLessonId(@Param("lessonId") Long id);

    @Query(nativeQuery = true, value = "select * from course where teacher_id = :teacherId")
    List<Course> findByTeacherId(Long teacherId);

    List<Course> findAllByTeacher_Id(Long teacherId);

    List<Course> findAllByLessonsContainsAndTeacher_Id(Lesson lesson, Long teacherId);

}
