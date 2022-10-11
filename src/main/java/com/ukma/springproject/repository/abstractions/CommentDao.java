package com.ukma.springproject.repository.abstractions;

import com.ukma.springproject.domain.Comment;

public interface CommentDao {
    void insert(Comment id);
    void update(int commentId, Comment id);
    void delete(int commentId);

    Comment findById(int commentId);
    Comment findByUserId(int userId);
}
