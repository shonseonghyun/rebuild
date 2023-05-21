package com.rebuild.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookReqPurchaseRequest {
    private String memberId;
    private String isbn13;

    public BookApplication toEntity() {
        return BookApplication.builder()
                .member_id(memberId)
                .isbn13(isbn13)
                .build();
    }
}
