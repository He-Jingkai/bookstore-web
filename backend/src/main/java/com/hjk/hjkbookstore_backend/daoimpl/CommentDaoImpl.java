package com.hjk.hjkbookstore_backend.daoimpl;

import com.hjk.hjkbookstore_backend.dao.CommentDao;
import com.hjk.hjkbookstore_backend.entity.Comment;
import com.hjk.hjkbookstore_backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentById(Integer id){
        return commentRepository.findCommentById(id);
    }
}