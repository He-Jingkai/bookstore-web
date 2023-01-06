package com.hjk.hjkbookstore_backend.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Node;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private String tagName;

    private Tag() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }


    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "RELATED")
    public Set<Tag> relatedTags;

    public void relates(Tag tag) {
        if (relatedTags == null) {
            relatedTags = new HashSet<>();
        }
        relatedTags.add(tag);
    }
}
