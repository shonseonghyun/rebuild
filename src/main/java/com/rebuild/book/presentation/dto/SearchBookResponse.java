package com.rebuild.book.presentation.dto;

import com.rebuild.book.domain.Book;
import lombok.Getter;

@Getter
public class SearchBookResponse {
    private Long bookId;
    private String author;
    private String publisher;
    private String title;
    private String content;
    private String state;

    private String dt_pub;



    public SearchBookResponse(Book entity){
        this.bookId= entity.getBookId();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.state = entity.getBookState().getDesc();
        this.dt_pub = entity.getDt_pub();
    }

}
