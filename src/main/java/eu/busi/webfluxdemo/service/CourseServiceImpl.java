package eu.busi.webfluxdemo.service;

import eu.busi.webfluxdemo.dao.entities.CourseEntity;
import eu.busi.webfluxdemo.dao.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Qualifier("Course")
public class CourseServiceImpl implements IService<CourseEntity,Long> {


    private ICourseRepository repository;
    @Autowired
    public CourseServiceImpl(ICourseRepository repository1){
        //this.repository = repository1;
    }

    @Override
    public Mono<CourseEntity> create(CourseEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Flux<CourseEntity> getAllEntities() {
        return repository.findAll();
    }

    @Override
    public Mono<CourseEntity> getOneEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<CourseEntity>> updateEntity(Long id, CourseEntity entity) {

        return repository.findById(id)
                .flatMap(existingCourse->{
                    existingCourse.setCreatedAt(entity.getCreatedAt())  ;
                    existingCourse.setTitle(entity.getTitle());
                    return repository.save(existingCourse);
                })
                .map(updatedCourse->new ResponseEntity<>(updatedCourse, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteOnEntity(Long id) {

        return repository.findById(id)
                .flatMap(existingCourse ->
                        repository.delete(existingCourse)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    public Flux<CourseEntity> streamAllEntities() {
        return repository.findAll();
    }
}
