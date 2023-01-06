package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.Tag;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface TagRepository extends Neo4jRepository<Tag, Long> {

    List<Tag> findByTagNameContaining(String tagName);

}
