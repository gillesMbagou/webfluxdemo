package eu.busi.webfluxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Course {
   private Long id;
   private String title;
   private LocalDateTime givenAt;

}
