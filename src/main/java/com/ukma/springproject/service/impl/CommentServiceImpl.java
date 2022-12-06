package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.repositories.CommentRepository;
import com.ukma.springproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Comment comment) {
        repository.save(comment);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        repository.findAll().forEach(comments::add);
        return comments;
    }

    @Override
    public List<Comment> findByUser(Long id) {
        return repository.readAllByUser_Id(id);
    }

    @Override
    public List<Comment> findByProduct(Long id) {
        return repository.readAllByProduct_Id(id);
    }
}
