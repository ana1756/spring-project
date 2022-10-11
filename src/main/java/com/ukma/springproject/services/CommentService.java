package com.ukma.springproject.services;

import com.ukma.springproject.domain.Comment;

public interface CommentService {
    void insert(Comment id);
    void update(int commentId, Comment id);
    void delete(int commentId);

    Comment findById(int commentId);
    Comment findByUserId(int userId);
}
