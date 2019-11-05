package eu.busi.webfluxdemo.dao.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "courses")
@Data
@AllArgsConstructor
public class CourseEntity {
          @Id
        private Long id;

        @NotBlank
        @Size(max = 140)
        private String title;

        @NotNull
        private Date createdAt = new Date();

        public CourseEntity() {

        }
}
