package com.rebuild.book.presentation;

import com.rebuild.book.application.SearchBookService;
import com.rebuild.book.application.dto.FindBookReqServiceDto;
import com.rebuild.book.presentation.dto.SearchBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rebuild/book")
public class BookController {

    private final SearchBookService searchBookService;

    @ResponseBody
    @GetMapping("show/id/{bookid}")
    public SearchBookResponse findBookByBookId(@PathVariable("bookid") Long bookId){
        return new SearchBookResponse(searchBookService.findBookById(new FindBookReqServiceDto(bookId)));
    }

//    @GetMapping("search/{target}/{query}")
//    @ResponseBody
//    public List<SearchBookResponse> findBookByTargetAndQuery(@PathVariable("target") String target, @PathVariable("query") String query){
//        return searchBookService.findBookByTitle(new SearchQuery(target,query));
//    }


//    @GetMapping("show/all")
//    @ResponseBody
//    public List<Book> searchAllBooks(){
//        return searchBookService.findAllBook();
//    }



//    @GetMapping("/rebuild/book/save/all/{title}")
//    public String saveBySelectedTitle(@PathVariable("title") String title){
//        return null;
//    }







}
