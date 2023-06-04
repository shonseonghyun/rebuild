package com.rebuild.book.application;


import com.rebuild.book.application.dto.FindBookDetailReqServiceDto;
import com.rebuild.book.domain.Book;
import com.rebuild.book.domain.BookRepository;
import com.rebuild.book.application.dto.FindBookReqServiceDto;
import com.rebuild.book.domain.dto.PaginationRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchBookService {

    private final BookRepository bookRepository;

    /* bookId로 도서 검색 */
    public Book findBookById(FindBookReqServiceDto findBookReqServiceDto){
        Book book = bookRepository.findById(findBookReqServiceDto.getBookId())
                .orElseThrow(()-> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        return book;
    }

    

    /* 특정 조건으로 검색 */
    public PaginationRes findBooksByDetailConditions(FindBookDetailReqServiceDto findBookDetailReqServiceDto){
        return bookRepository.findBooksByDetailConditions(findBookDetailReqServiceDto);
    }





    /* 도서 전체 검색 */
//    public List<Book> findAllBook() { //원랜 protected나 default로 했으나 BookController에서 접근제어자 때문에 사용이 불가하여 public변ㄱㅇ
//            Sort sort1 = Sort.by("title");
////        return  bookRepository.findAll();
//    }


}
