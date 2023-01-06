package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.BookDetail;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface BookDetailService {

    BookDetail findBookDetailById(Integer id);

    List<BookDetail> findAll();

    void saveABook(Map<String,Object> map) throws SolrServerException, IOException;

    void newABook(Map<String,Object> map) throws SolrServerException, IOException;

    void deleteById(Integer id) throws SolrServerException, IOException ;

}
