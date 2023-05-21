package com.rebuild.book.domain;

import com.rebuild.book.domain.BookState;
import jakarta.persistence.AttributeConverter;

public class BookStateConverter implements AttributeConverter<BookState,String> {

    //ENUM -> DB데이터
    @Override
    public String convertToDatabaseColumn(BookState attribute) {
        return attribute.getState();
    }

    //DB데이터 -> ENUM
    @Override
    public BookState convertToEntityAttribute(String dbData) {
        return BookState.ofBookState(dbData);
    }
}
