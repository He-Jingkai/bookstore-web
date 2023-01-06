package com.hjk.hjkbookstore_backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.service.BookDetailService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
public class BookDetailController {

    @Autowired
    private BookDetailService bookDetailService;

    @Value("${web.upload-path}")
    private String uploadPath;

    @CrossOrigin
    @RequestMapping("/bookdetail")
    public BookDetail getBookDetail(@RequestParam("bookid") String bookid) { return bookDetailService.findBookDetailById(Integer.valueOf(bookid)); }

    @CrossOrigin
    @RequestMapping("/alldetail")
    public List<BookDetail> getAllBookDetail() {
        return bookDetailService.findAll();
    }

    @CrossOrigin
    @RequestMapping("/saveone")
    public void saveOne(@RequestBody Map<String,Object> map) throws SolrServerException, IOException { bookDetailService.saveABook(map); }

    @CrossOrigin
    @RequestMapping("/newone")
    public void newOne(@RequestBody Map<String,Object> map) throws SolrServerException, IOException { bookDetailService.newABook(map); }

    @CrossOrigin
    @RequestMapping("/deletebook")
    public String delete(@RequestParam("bookid") Integer id) throws SolrServerException, IOException {
        bookDetailService.deleteById(id);
        return "done";
    }

    @CrossOrigin
    @RequestMapping (value = "/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "avatar") MultipartFile avatar) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime()) + avatar.getOriginalFilename();
        String filePath=uploadPath+dateName;

        File newFile = new File(filePath);
        try { avatar.transferTo(newFile); }
        catch (IllegalStateException | IOException e) { e.printStackTrace(); }

        String url = "http://localhost:8080/" + dateName;
        return JSON.toJSONString(url, SerializerFeature.BrowserCompatible);
    }
}
