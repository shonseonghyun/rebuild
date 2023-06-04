package com.rebuild.book.domain.dto;

import com.rebuild.book.domain.BookState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindBooksByDetailConditionsRes {
    Long bookId;
    String title;
    String author;
    String publisher;
    String dt_pub;
    BookState state;
    int totalCount;
}
