package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tbook")
public class Book {
   @Id
   @Field
   private String id;

   @Field
   @DBRef
   private Author author;

   @Field
   @DBRef
   private Genre genre;

   @Field
   private String name;
}
