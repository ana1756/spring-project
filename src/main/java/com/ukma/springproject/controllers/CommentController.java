package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.services.CommentService;
import com.ukma.springproject.services.exceptions.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    Comment createComment(@Valid @RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    ResponseEntity<String> handleApplicationException(CommentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleApplicationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
