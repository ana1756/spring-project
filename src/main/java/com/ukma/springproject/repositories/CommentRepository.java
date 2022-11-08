package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.domain.User;
import org.hibernate.type.StringNVarcharType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByUserId(Long id);
}
