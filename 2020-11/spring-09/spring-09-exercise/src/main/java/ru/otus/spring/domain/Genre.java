package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tGenre")
public class Genre {
    @Id
    @Column(name = "GenreID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;

    @Column(name = "Name")
    private String Name;
}
