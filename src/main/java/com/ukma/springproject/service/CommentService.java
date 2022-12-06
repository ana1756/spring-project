package com.ukma.springproject.service;

import com.ukma.springproject.domain.Comment;
import java.util.List;

public interface CommentService {

    void create(Comment comment);
    void delete(Long id);
    Comment findById(Long id);
    List<Comment> findAll();
    List<Comment> findByUser(Long id);
    List<Comment> findByProduct(Long id);

}
