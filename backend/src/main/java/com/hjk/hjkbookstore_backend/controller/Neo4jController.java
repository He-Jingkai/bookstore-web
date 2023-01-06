//package com.hjk.hjkbookstore_backend.controller;
//
//import com.hjk.hjkbookstore_backend.entity.Tag;
//import com.hjk.hjkbookstore_backend.repository.TagRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
///*Only used to initialize neo4j */
//@RestController
//public class Neo4jController {
//    @Autowired
//    private TagRepository tagRepository;
//
//    @CrossOrigin
//    @RequestMapping("/addToNeo4j")
//    public void addToNeo4j() {
//        tagRepository.deleteAll();
//
//        Tag python = new Tag("python");
//        Tag algorithms = new Tag("algorithms");
//        Tag cpp = new Tag("cpp");
//        Tag java = new Tag("java");
//        Tag computation = new Tag("computation");
//        Tag circuit = new Tag("circuit");
//        Tag literature = new Tag("literature");
//        tagRepository.save(python);
//        tagRepository.save(algorithms);
//        tagRepository.save(cpp);
//        tagRepository.save(java);
//        tagRepository.save(computation);
//        tagRepository.save(circuit);
//        tagRepository.save(literature);
//
//        Tag algorithms1 = tagRepository.findByTagName("algorithms");
//        algorithms1.relates(tagRepository.findByTagName("python"));
//        algorithms1.relates(tagRepository.findByTagName("cpp"));
//        algorithms1.relates(tagRepository.findByTagName("java"));
//        tagRepository.save(algorithms1);
//
//        Tag python1 = tagRepository.findByTagName("python");
//        python1.relates(tagRepository.findByTagName("computation"));
//        tagRepository.save(python1);
//
//        Tag computation1 = tagRepository.findByTagName("computation");
//        computation1.relates(tagRepository.findByTagName("circuit"));
//        computation1.relates(tagRepository.findByTagName("literature"));
//        tagRepository.save(computation1);
//    }
//}
