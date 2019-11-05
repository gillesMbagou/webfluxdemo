package eu.busi.webfluxdemo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IService<T, Key> { //all crud methods
 Mono<T> create(T entity);
 Flux<T> getAllEntities();
 Mono<T> getOneEntity(Key id);
 Mono<ResponseEntity<T>> updateEntity(Key id, T entity);
 Mono<ResponseEntity<Void>> deleteOnEntity(Key id);

 Flux<T> streamAllEntities();

}
