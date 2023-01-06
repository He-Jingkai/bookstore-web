package com.hjk.hjkbookstore_backend.controller;

import com.hjk.hjkbookstore_backend.Dto.TagAndBooks;
import com.hjk.hjkbookstore_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @CrossOrigin
    @RequestMapping("/findRelatedBooks")
    public TagAndBooks findRelatedBooks(@RequestParam("tagName") String tagName) {
        return tagService.firstAndSecondRelatedTagAndBooks(tagName);
    }
}
