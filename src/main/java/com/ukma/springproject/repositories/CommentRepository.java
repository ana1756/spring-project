package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByUser(User user);
}
