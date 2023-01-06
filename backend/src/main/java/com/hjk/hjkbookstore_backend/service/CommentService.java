package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentById(Integer id);
}
