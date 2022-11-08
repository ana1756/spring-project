package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.CommentRepository;
import com.ukma.springproject.services.CommentService;
import com.ukma.springproject.services.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment " + id + " does nor exist"));
    }

    @Override
    public List<Comment> findByUser(Long id) {
        return commentRepository.findAllByUserId(id);
    }
}
