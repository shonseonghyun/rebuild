package com.rebuild.loan.presentation;

import com.rebuild.loan.CurrentLoanBookListResponse;
import com.rebuild.loan.application.dto.LoanBookReqServiceDto;
import com.rebuild.loan.application.LoanReturnService;
import com.rebuild.loan.application.dto.ReturnBookReqServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/rebuild") //모두 제거
@RequiredArgsConstructor
public class BookLoanController {

    private final LoanReturnService loanReturnService;


    @PostMapping("/rebuild/loan/{memberId}/{bookId}")
    public String loanBook(@PathVariable("memberId") Long memberId,@PathVariable("bookId") Long bookId){
        loanReturnService.loanBook(new LoanBookReqServiceDto(memberId,bookId));
        return "하이";
    }

    @PostMapping("/rebuild/return/{memberId}/{bookId}")
    public String returnBook(@PathVariable("memberId") Long memberId,@PathVariable("bookId") Long bookId){
        loanReturnService.returnBook(new ReturnBookReqServiceDto(memberId,bookId));
        return "하이";
    }

    //현재 도서 대여목록 보여주기
//    @GetMapping("/rebuild/loan/{memberId}")
//    public List<CurrentLoanBookListResponse> showCurrentLoanBookList(@PathVariable("memberId") Long memberId){
//        return loanReturnService.showCurrentLoanBookList(memberId);
//    }

}
