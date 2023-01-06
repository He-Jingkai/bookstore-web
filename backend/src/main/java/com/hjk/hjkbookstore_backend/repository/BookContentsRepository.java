package com.hjk.hjkbookstore_backend.repository;
import com.hjk.hjkbookstore_backend.entity.BookContents;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookContentsRepository extends MongoRepository<BookContents, Integer> {

}
