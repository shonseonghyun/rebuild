package com.rebuild.purchase;

import com.rebuild.purchase.externalAPI.aladin.BookSearchApiHandler;
import com.rebuild.purchase.externalAPI.aladin.AladinExternalApiService;
import com.rebuild.purchase.externalAPI.kakao.KakaoExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rebuild/req")
public class BookPurchaseController {
    private final BookReqPurchaseService bookReqPurchaseService;

/*
   "외부API"를 통한 도서조회
    1. 제목
    2. isbn13
*/
    @GetMapping("search/{target}/{query}")
    public <T> T search(@PathVariable("target") String target, @PathVariable("query") String query){
        BookSearchApiHandler searchHandler = new KakaoExternalApiService()
                .setNext(new AladinExternalApiService());

        T content = searchHandler.process(new SearchQuery(target,query));
        System.out.println(content);
        return content;
    }


    //외부 도서 조회 후 원하는 도서 신청(save)
    @PostMapping("purchase/{memberId}/{isbn}")
    public String reqPurchaseBook(@PathVariable("memberId") String memberId,@PathVariable("isbn") String isbn){ //NeedRefactoring MemberId가 현재 로그인되어있으며 유효한 사용자인지 검증 필요
        return bookReqPurchaseService.reqPurchaseBook(new BookReqPurchaseRequest(memberId,isbn));
    }


    //유저의 도서신청 이력 조회
//    @GetMapping("show/{memberId}")
//    public List<PurchaseBook> findAllBookReqListByMemberId(@PathVariable("memberId") String memberId){
//        return bookReqPurchase.findAllBookReqListByMemberId(memberId);
//    }
}
