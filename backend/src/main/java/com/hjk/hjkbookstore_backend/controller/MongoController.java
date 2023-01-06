//package com.hjk.hjkbookstore_backend.controller;
//
//import com.hjk.hjkbookstore_backend.entity.BookContents;
//import com.hjk.hjkbookstore_backend.repository.BookContentsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///*Only used to initialize mongodb */
//@RestController
//public class MongoController {
//    @Autowired
//    private BookContentsRepository bookContentsRepository;
//    @CrossOrigin
//    @RequestMapping("/addToMongo")
//    public void addToMongo() {
//       for(int i = 1; i<9; i++){
//           BookContents bookContents=new BookContents(i,"Book Contents Test"+i);
//           bookContentsRepository.save(bookContents);
//       }
//    }
//}
//
//
