package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.BookDetailDao;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.service.SolrService;
import com.hjk.hjkbookstore_backend.utils.SolrBookItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private BookDetailDao bookDetailDao;
    private final SolrClient client = getSolrClient();

    @Override
    public void addOrUpdate(Integer id) throws SolrServerException, IOException {
        client.addBean("bookstore", new SolrBookItem(bookDetailDao.findOne(id)));
        client.commit("bookstore");
    }

    @Override
    public void addAll() throws SolrServerException, IOException {
        List<BookDetail> bookDetails=bookDetailDao.findAll();
        for (BookDetail bookDetail:bookDetails)
            client.addBean("bookstore", new SolrBookItem(bookDetail));
        client.commit("bookstore");
    }

    @Override
    public void delete(Integer id) throws SolrServerException, IOException {
        client.deleteById("bookstore",String.valueOf(id));
        client.commit("bookstore");
    }

    @Override
    public List<Integer> query(String needle) throws SolrServerException, IOException {
        final Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "description:*"+needle+"*");
        queryParamMap.put("fl", "id, description");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
        final QueryResponse response = client.query("bookstore", queryParams);
        final SolrDocumentList documents = response.getResults();
        List<Integer> ids=new ArrayList<>();
        System.out.println("Found " + documents.getNumFound() + " documents");
        for (SolrDocument document : documents)
            ids.add(Integer.parseInt((String) document.getFirstValue("id")));
        return ids;
    }


    public static SolrClient getSolrClient() {
        final String solrUrl = "http://localhost:8983/solr";
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
}
