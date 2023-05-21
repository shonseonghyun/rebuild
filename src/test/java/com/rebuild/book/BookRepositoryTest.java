package com.rebuild.book;

import com.rebuild.book.domain.Book;
import com.rebuild.book.domain.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void publisher_get(){
        System.out.println("시작");
        List<Book> list = bookRepository.findByPublisher("출판");
        for(Book b : list) System.out.println(b.toString());
    }
    @Test
    public void title_검증(){
        System.out.println("시작");
        List<Book> list = bookRepository.findByTitleContainingOrderByBookStateAsc("tes");

        for(Book b : list) System.out.println(b.toString());

        List<Book> lists = bookRepository.findByTitleContainingOrderByBookStateDesc("tes");
        System.out.println(lists.size());
        for(Book b : lists) System.out.println(b.toString());
    }

    @Test
    public void pagetest(){
        List<Book> list = bookRepository.findByTitleContaining("te", PageRequest.of(0,2));
        for(Book b: list){
            System.out.println(b.toString());
        }

        List<Book> list2 = bookRepository.findByTitleContaining("te", PageRequest.of(1,2));
        for(Book b: list2){
            System.out.println(b.toString());
        }
    }
}