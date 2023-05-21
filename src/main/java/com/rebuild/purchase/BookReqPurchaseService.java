package com.rebuild.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReqPurchaseService {

    private final BookPurchaseRepository bookPurchaseRepository;

    public String reqPurchaseBook(BookReqPurchaseRequest reqDTO){
        //해당 멤버 도서 구매 횟수를 넘었나?

        //도서 구매요청목록에 이미 존재하는지 확인 요망
        if(isAlreadyReqBook(reqDTO.getIsbn13())){
            return "신청 불가";
        }
        System.out.println("신청가능한 도서입니다.");

        Long id = bookPurchaseRepository.save(reqDTO.toEntity()).getId(); //NeedRefactoing 흠.. 생각보다 도서 신청날짜를 찾는게 어렵네? 수정해야할거같다..
        return "신청 완료"+id.toString();
    }

    public List<BookApplication> findAllBookReqListByMemberId(String memberId){
        return bookPurchaseRepository.findAllBookReqListByMemberId(memberId);
    }

    private boolean isAlreadyReqBook(String isbn13){
        int cnt = bookPurchaseRepository.findByIsbn13(isbn13);
        return cnt>0;
    }
}
