package com.rebuild.purchase.externalAPI.aladin;

import com.rebuild.purchase.SearchQuery;

public abstract  class BookSearchApiHandler {
    BookSearchApiHandler next;
    
    public BookSearchApiHandler setNext(BookSearchApiHandler nextHandler){
        next = nextHandler;
        return this;
    }

    public <T> T process(SearchQuery searchQuery){
        T content = this.searchBooks(searchQuery);

        if(content == null){ //만약 선API에서 조회 실패하여 Null을 리턴한 경우
            if(next!=null){
                return next.process(searchQuery);
            }
        }

        return content;
    }

    protected abstract <T> T searchBooks(SearchQuery searchQuery);
}
