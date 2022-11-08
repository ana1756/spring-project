package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.services.CommentService;
import com.ukma.springproject.services.exceptions.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    Comment createComment(@Valid @RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        var dummy = new Comment();
        dummy.setId(id);
        commentService.delete(dummy);
    }

    @GetMapping("/{id}")
    Comment getById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @GetMapping("/user/{id}")
    List<Comment> getAllByUserId(@PathVariable Long id) {
        return commentService.findByUser(id);
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
