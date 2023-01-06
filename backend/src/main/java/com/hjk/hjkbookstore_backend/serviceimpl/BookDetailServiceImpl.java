package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.BookDetailDao;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.service.BookDetailService;
import com.hjk.hjkbookstore_backend.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BookDetailServiceImpl implements BookDetailService {

    @Autowired
    private BookDetailDao bookDetailDao;

    @Autowired
    private SolrService solrService;

    @Override
    public BookDetail findBookDetailById(Integer id){ return bookDetailDao.findOne(id);}

    @Override
    public List<BookDetail> findAll(){return bookDetailDao.findAll();}

    @Override
    public void saveABook(Map<String,Object> map) throws SolrServerException, IOException {
        Integer id=(Integer) map.get("id");
        String image=(String)map.get("image");
        String title=(String)map.get("title");
        Integer price=(Integer) map.get("price");
        String author=(String)map.get("author");
        String description=(String)map.get("description");
        String content=(String)map.get("content");
        String writer=(String)map.get("writer");
        Integer inventory=(Integer) map.get("inventory");
        String isbn=(String)map.get("isbn");

        BookDetail bookDetail=bookDetailDao.findOne(id);
        bookDetail.setImage(image);
        bookDetail.setTitle(title);
        bookDetail.setPrice(price);
        bookDetail.setAuthor(author);
        bookDetail.setDescription(description);
        bookDetail.setContents(content);
        bookDetail.setWriter(writer);
        bookDetail.setInventory(inventory);
        bookDetail.setIsbn(isbn);
        BookDetail bookDetail1=bookDetailDao.saveOne(bookDetail,1);

        solrService.addOrUpdate(bookDetail1.getId());
    }

    @Override
    public void newABook(Map<String,Object> map) throws SolrServerException, IOException {
        String image=(String)map.get("image");
        String title=(String)map.get("title");
        Integer price=(Integer)map.get("price");
        String author=(String)map.get("author");
        String description=(String)map.get("description");
        String content=(String)map.get("content");
        String writer=(String)map.get("writer");
        Integer inventory=(Integer) map.get("inventory");
        String isbn=(String)map.get("isbn");

        BookDetail bookDetail=new BookDetail();
        bookDetail.setImage(image);
        bookDetail.setTitle(title);
        bookDetail.setPrice(price);
        bookDetail.setAuthor(author);
        bookDetail.setDescription(description);
        bookDetail.setContents(content);
        bookDetail.setWriter(writer);
        bookDetail.setInventory(inventory);
        bookDetail.setIsbn(isbn);
        BookDetail bookDetail1=bookDetailDao.saveOne(bookDetail,0);

        solrService.addOrUpdate(bookDetail1.getId());

    }

    @Override
    public void deleteById(Integer id) throws SolrServerException, IOException {
        BookDetail bookDetail=bookDetailDao.findOne(id);
        bookDetailDao.delete(bookDetail);

        solrService.delete(id);
    }

}
