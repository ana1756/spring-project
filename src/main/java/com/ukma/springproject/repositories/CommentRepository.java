package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> readAllByUser_Id(Long id);
    List<Comment> readAllByProduct_Id(Long id);
}
