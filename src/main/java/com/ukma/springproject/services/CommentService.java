package com.ukma.springproject.services;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);
    void delete(Comment comment);
    Comment findById(Long id);
    List<Comment> findByUser(User user);
}
