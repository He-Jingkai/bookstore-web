package com.hjk.hjkbookstore_backend.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SolrService {
    void addOrUpdate(Integer id) throws SolrServerException, IOException;

    void delete(Integer id) throws SolrServerException, IOException;

    /*只用于将数据*/
    void addAll() throws SolrServerException, IOException;

    List<Integer> query(String needle) throws SolrServerException, IOException;
}
