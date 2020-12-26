package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tBookComment")
public class BookComment {
    @Id
    @Column(name = "BookCommentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookCommentId;

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "BookID")
    private Book book;

    @Column(name = "Comment")
    private String comment;
}

