package ru.otus.spring.dao;

import ru.otus.spring.domain.BookComment;

import java.util.ArrayList;
import java.util.List;

public class BookCommentJpa implements BookCommentDao {
    @Override
    public boolean insert(BookComment bc) {
        boolean result = false;
        try {

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(BookComment bc) {
        boolean result = false;
        try {

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BookComment> getAll() {
        ArrayList<BookComment> res = new ArrayList<BookComment>();
        return res;
    }

    @Override
    public boolean deleteById(long bookCommentId) {
        boolean result = false;
        try {

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
