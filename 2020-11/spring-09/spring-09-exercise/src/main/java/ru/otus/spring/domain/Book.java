package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

   @Fetch(FetchMode.SELECT)
   @BatchSize(size = 10)
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "AuthorID", referencedColumnName = "AuthorID")
   private Author author;

   @Fetch(FetchMode.SELECT)
   @BatchSize(size = 10)
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "GenreID", referencedColumnName = "GenreID")
   private Genre genre;

   @Column(name = "Name")
   private String name;
}
