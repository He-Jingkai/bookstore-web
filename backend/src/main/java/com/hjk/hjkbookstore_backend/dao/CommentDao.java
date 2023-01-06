package com.hjk.hjkbookstore_backend.dao;

import com.hjk.hjkbookstore_backend.entity.Comment;
import java.util.List;

public interface CommentDao {
    List<Comment> findCommentById(Integer id);
}