package eu.busi.webfluxdemo.dao.repository;

import eu.busi.webfluxdemo.dao.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends ReactiveMongoRepository<CourseEntity,Long> {
}
