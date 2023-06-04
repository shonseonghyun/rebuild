package com.rebuild.book.presentation;

import com.rebuild.book.application.SearchBookService;
import com.rebuild.book.application.dto.FindBookReqServiceDto;
import com.rebuild.book.domain.dto.PaginationRes;
import com.rebuild.book.presentation.dto.SearchBookResponse;
import com.rebuild.book.presentation.dto.FindBookDetailsReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final SearchBookService searchBookService;

    @ResponseBody
    @GetMapping("/rebuild/book/show/book/{bookid}")
    public SearchBookResponse findBookByBookId(@PathVariable("bookid") Long bookId){
        return new SearchBookResponse(searchBookService.findBookById(new FindBookReqServiceDto(bookId)));
    }

    @ResponseBody
    @PostMapping("/rebuild/book/search/detailSearch")
    public PaginationRes findBooksByDetailConditions( FindBookDetailsReqDto findBookDetailsReqDto){
        return searchBookService.findBooksByDetailConditions(findBookDetailsReqDto.toServiceDto());
    }
}
