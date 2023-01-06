package com.hjk.hjkbookstore_backend.utils;

import com.hjk.hjkbookstore_backend.entity.BookDetail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

@Data
@NoArgsConstructor
public class SolrBookItem {
    @Field
    public int id;
    @Field
    public String description;

    public SolrBookItem(BookDetail bookDetail) {
        this.id = bookDetail.getId();
        this.description = bookDetail.getDescription();
    }
}
