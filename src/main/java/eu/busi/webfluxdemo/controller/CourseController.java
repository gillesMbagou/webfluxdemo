package eu.busi.webfluxdemo.controller;

import eu.busi.webfluxdemo.dao.entities.CourseEntity;
import eu.busi.webfluxdemo.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController("/courses")
public class CourseController { // controller endpoints

    private CourseServiceImpl service;
    @Autowired
public CourseController(  CourseServiceImpl service1){
    this.service = service1;
}

    @GetMapping
    public Flux<CourseEntity> getAllCourses() {
       return service.getAllEntities();
    }

    @PostMapping
    public Mono<CourseEntity> createCourse(@Valid @RequestBody CourseEntity course){
        return service.create(course);
    }

    @GetMapping("/{id}")
    public Mono<CourseEntity> getCourseById(@PathVariable(value = "id") Long id){
        return service.getOneEntity(id);
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CourseEntity>> updateCourse(@PathVariable(value = "id") Long id,
                                                           @Valid @RequestBody CourseEntity course){
        return service.updateEntity(id,course);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCourse(@PathVariable(value = "id") Long id){
        return service.deleteOnEntity(id);
    }

    //sends the courses in the form of Server Sent Events to a browser like this -
    /*data: {"id":"59ba5389d2b2a85ed4ebdafa","title":"BuSI Test Academy","createdAt":1505383305602}
    data: {"id":"59ba5587d2b2a85f93b8ece7","title":"BuSI Teacher the best","createdAt":1505383814847}*/

    @GetMapping(value = "/stream/courses",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CourseEntity> streamAllCourses(){
        return service.getAllEntities();
   }
}
