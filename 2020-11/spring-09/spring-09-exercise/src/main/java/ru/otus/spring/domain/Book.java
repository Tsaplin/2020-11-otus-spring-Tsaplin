package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbook")
public class Book {
   @Id
   @Column(name = "BookID")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long bookId;

   @ManyToOne
   @JoinColumn(name = "AuthorID", referencedColumnName = "AuthorID")
   private Author author;

   @ManyToOne
   @JoinColumn(name = "GenreID", referencedColumnName = "GenreID")
   private Genre genre;

   @Column(name = "Name")
   private String name;
}
