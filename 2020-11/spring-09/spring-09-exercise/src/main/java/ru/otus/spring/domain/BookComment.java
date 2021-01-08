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
@Table(name = "tbookcomment")
public class BookComment {
    @Id
    @Column(name = "bookcommentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookCommentId;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookID", referencedColumnName = "BookID")
    private Book book;

    @Column(name = "Comment")
    private String comment;
}

