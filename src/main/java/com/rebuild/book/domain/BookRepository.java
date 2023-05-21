package com.rebuild.book.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//CC_TABLE_TABLE
public interface BookRepository extends JpaRepository<Book, Long> {
    //NeedRefactoring pring Data JPA의 쿼리 로 리팩토링
    @Query(value="select * from cc_book_table where title like :query", nativeQuery = true)
    List<Book> findByTitle(@Param("query") String query);


    List<Book> findByTitleContaining(String title, Pageable pageable);

    /*test */
    List<Book> findByPublisher(String publisher);
    List<Book> findByTitleContainingOrderByBookStateAsc(String title);
    List<Book> findByTitleContainingOrderByBookStateDesc(String title);

}
